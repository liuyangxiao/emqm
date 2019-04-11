package com.burning.emqmsg.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.utils.*
import com.burning.emqmsg.utils.MatUtils.REQUEST_CODE_CHOOSE
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.httpservice.impl.HttpUpload
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import com.burning.realmdatalibrary.httpservice.requbean.UpdataUser
import com.burning.realmdatalibrary.po.LoginUserPo
import com.burning.realmdatalibrary.po.UserPo
import com.burning.realmdatalibrary.po.UserRemarksPo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import com.zyao89.view.zloading.ZLoadingDialog
import com.zyao89.view.zloading.Z_TYPE
import kotlinx.android.synthetic.main.activity_userinfo.*
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File


class UserinfoActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_userinfo
    private val REQUEST_CODE_SETUSER = 3334

    companion object {
        var USER_ID = "userid"
        val SET_USER_STRING = "setuser"
    }

    var userapi = UserApimpl()
    @SuppressLint("SetTextI18n")
    override fun init() {
        LiveDataBus.get().with("TAG", String.javaClass)
                .observe(this, Observer {
                    print("=============TAG=====$it")
                })


        val longExtra = intent.getLongExtra(USER_ID, 0)
        if (longExtra == 0L) {
            return
        }

        val findFirst = realm.where(UserPo::class.java).equalTo("id", longExtra).findFirst()
        user_info_username.text = "${findFirst.username}"
        if (longExtra == UserInfo.userid) {
            user_info_username.text = "${findFirst.username}"
            userinfo_righ_bt.visibility = View.GONE
            //  userinfo_left_bt.text    = "修改信息"
            userinfo_left_bt.visibility = View.GONE
            user_info_usericon.setOnClickListener {


                Matisse.from(this)
                        .choose(MimeType.ofAll())//图片类型
                        .countable(true)//true:选中后显示数字;false:选中后显示对号
                        .maxSelectable(1)//可选的最大数
                        .capture(true)//选择照片时，是否显示拍照
                        .captureStrategy(CaptureStrategy(true, "com.burning.emqmsg.Myfileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                        .imageEngine(MatGlideEngine())//图片加载引擎
                        .forResult(REQUEST_CODE_CHOOSE)


//
                //  Matisse.from(this)
//                        .choose(MimeType.ofAll())//图片类型
//                        .countable(true)//true:选中后显示数字;false:选中后显示对号
//                        .maxSelectable(1)//可选的最大数
//                        .capture(true)//选择照片时，是否显示拍照
//                        .captureStrategy(CaptureStrategy(true, "com.burning.emqmsg.Myfileprovider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
//                        .imageEngine(MatGlideEngine())//图片加载引擎
//                        .forResult(REQUEST_CODE_CHOOSE)
                //    MatUtils.getIcon(this@UserinfoActivity, 1)
            }
            re_userinfo_gender.setOnClickListener {
                //性别
                var intent = Intent(this@UserinfoActivity, SetUserInfoActivity::class.java)
                intent.putExtra(SetUserInfoActivity.SET_USER, 1)
                startActivityForResult(intent, REQUEST_CODE_SETUSER)

            }
            re_userinfo_age.setOnClickListener {

                //年龄
                var intent = Intent(this@UserinfoActivity, SetUserInfoActivity::class.java)
                intent.putExtra(SetUserInfoActivity.SET_USER, 2)
                startActivityForResult(intent, REQUEST_CODE_SETUSER)
            }
            re_userinfo_name.setOnClickListener {
                //昵称
                var intent = Intent(this@UserinfoActivity, SetUserInfoActivity::class.java)
                intent.putExtra(SetUserInfoActivity.SET_USER, 3)
                startActivityForResult(intent, REQUEST_CODE_SETUSER)
            }
            re_userinfo_ac_reams.setOnClickListener {
                //签名
                var intent = Intent(this@UserinfoActivity, SetUserInfoActivity::class.java)
                intent.putExtra(SetUserInfoActivity.SET_USER, 4)
                startActivityForResult(intent, REQUEST_CODE_SETUSER)
            }
            bz_userinfo_tv.text = "昵称 :${findFirst.username}"

        } else {
            val userRemarksPo = realm.where(UserRemarksPo::class.java).equalTo("loginuserPoId", UserInfo.userid).equalTo("userPoId", longExtra).findFirst()
            bz_userinfo_tv.text = "备注 :${userRemarksPo?.remarksPo}"

            for (groupPo in realm.where(LoginUserPo::class.java).equalTo("userid", UserInfo.userid).findFirst().groupPos.where().equalTo("type", 1).findAll()) {
                if (groupPo.userlis.contains(findFirst)) {
                    //是好友
                    userinfo_left_bt.visibility = View.GONE
                }
            }
        }
        userinfo_gender.text = "性别 : " +
                if (findFirst.gender == "1") {
                    "男"
                } else {
                    "女"
                }
        user_info_userreid.text = "私密ID : ${findFirst.setID}"
        userinfo_ac_reams.text = "签名 : ${findFirst.userdesc}"
        tv_userinfo_age.text = "年龄 :${findFirst.age}"
        Glide.with(this@UserinfoActivity).load(ImageConfig.Image_path + findFirst.icon).into(user_info_usericon as ImageView)
        userinfo_left_bt.setOnClickListener {
            if (longExtra == UserInfo.userid) {
                //
            } else {
                var intent = Intent(this, AddFrendActivity::class.java)
                intent.putExtra(AddFrendActivity.USER_ID, longExtra)
                startMyActivity(intent)
            }
        }
        userinfo_righ_bt.setOnClickListener {
            var intent = Intent(this, MsgActivity::class.java)
            intent.putExtra(MsgActivity.USER_ID, longExtra)
            startMyActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        particleView?.resume()
    }

    override fun onPause() {
        super.onPause()
        particleView?.pause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MatUtils.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //头像
            var list = Matisse.obtainResult(data)
            if (list == null || list.isEmpty()) {
                Toast.makeText(applicationContext, "头像设置失败", Toast.LENGTH_SHORT).show()
                return
            }
            val uriToFile = UriUtils.getRealFilePath(list[0], this)
            lubanComImage(uriToFile)
            // upsetUsericon(uriToFile)
        } else if (requestCode == REQUEST_CODE_SETUSER && resultCode == RESULT_OK) {
            var setID = data?.getIntExtra(SET_USER_STRING, 0)
            if (setID == 0)
                return
            var setString = data?.getStringExtra(SetUserInfoActivity.SET_USER)
            var user = UpdataUser()
            user.id = UserInfo.userid
            when (setID) {
                1 ->
                    user.gender = setString
                2 ->
                    user.age = setString!!.toInt()
                3 ->
                    user.username = setString
                4 ->
                    user.userdesc = setString
            }
            setUser(user, setID!!)
        }
    }

    fun lubanComImage(uriToFile: File) {
        var dialog = ZLoadingDialog(this@UserinfoActivity)
        Luban.with(this@UserinfoActivity)
                .load(uriToFile)
                .ignoreBy(100)
                .setTargetDir(ImageConfig.getCompressJpgFileAbsolutePath())
                .filter { path -> !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif")) }
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {

                        dialog?.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
                                ?.setLoadingColor(Color.BLUE)//颜色
                                ?.setHintText("图片压缩...")
                                ?.show()
                    }

                    override fun onSuccess(file: File) {
                        dialog.dismiss()
                        upsetUsericon(file)
                    }

                    override fun onError(e: Throwable) {
                        dialog.dismiss()
                    }
                }).launch()
    }

    fun upsetUsericon(uriToFile: File) {
        var dialog = ZLoadingDialog(this@UserinfoActivity)
        dialog?.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
                ?.setLoadingColor(Color.BLUE)//颜色
                ?.setHintText("头像保存...")
                ?.show()
        HttpUpload.upload(uriToFile) { i: Int, s: String, data1: String ->
            if (i == 200 && data1 != null) {
                var user = UpdataUser()
                user.icon = data1
                user.id = UserInfo.userid
                userapi.updataUser(user) { i: Int, s: String, data: String ->
                    if (i == 200) {
                        Glide.with(this@UserinfoActivity).load(uriToFile).into(user_info_usericon as ImageView)
                        RxReamlUtils.updata {
                            val findFirst1 = it.where(UserPo::class.java).equalTo("id", UserInfo.userid).findFirst()
                            findFirst1.icon = data1
                        }
                    } else {
                        Toast.makeText(applicationContext, "头像设置失败", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
            } else {
                dialog.dismiss()
                Toast.makeText(applicationContext, "头像设置失败", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun setUser(user: UpdataUser, set: Int) {

        var dialog = ZLoadingDialog(this@UserinfoActivity)
        dialog?.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
                ?.setLoadingColor(Color.BLUE)//颜色
                ?.setHintText("信息设置...")
                ?.show()
        userapi.updataUser(user) { i: Int, s: String, data: String ->
            when (set) {
                1 -> {
                    userinfo_gender.text = "性别 : " +
                            if ("1" == user.gender) {
                                "男"
                            } else {
                                "女"
                            }
                }
                2 -> tv_userinfo_age.text = "年龄: ${user.age}"
                3 -> {
                    bz_userinfo_tv.text = "昵称 :${user.username}"
                    user_info_username.text = "${user.username}"

                }
                4 -> userinfo_ac_reams.text = "签名 : ${user.userdesc}"
            }
            dialog.dismiss()
            if (i == 200) {
                RxReamlUtils.updata {
                    var findFirst1 = it.where(UserPo::class.java).equalTo("id", UserInfo.userid).findFirst()
                    when (set) {
                        1 -> findFirst1.gender = user.gender
                        2 -> findFirst1.age = user.age
                        3 -> findFirst1.username = user.username
                        4 -> findFirst1.userdesc = user.userdesc
                    }
                }
            } else {
                Toast.makeText(applicationContext, "信息设置失败", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
