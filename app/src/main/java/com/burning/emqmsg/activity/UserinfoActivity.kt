package com.burning.emqmsg.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.view.View
import com.bumptech.glide.Glide
import com.burning.emqmsg.R
import com.burning.emqmsg.utils.MatGlideEngine
import com.burning.emqmsg.utils.UriUtils
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.UserPo
import com.burning.realmdatalibrary.po.UserRemarksPo
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy
import kotlinx.android.synthetic.main.activity_userinfo.*


class UserinfoActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_userinfo
    private val REQUEST_CODE_CHOOSE = 33252

    companion object {
        var USER_ID = "userid"
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        val longExtra = intent.getLongExtra(USER_ID, 0)
        if (longExtra == 0L) {
            return
        }

        val findFirst = realm.where(UserPo::class.java).equalTo("id", longExtra).findFirst()
        user_info_username.text = "${findFirst.username}"
        if (longExtra == UserInfo.userid) {
            user_info_username.text = "自己 : ${findFirst.username}"
            userinfo_righ_bt.visibility = View.GONE
            //  userinfo_left_bt.text    = "修改信息"
            userinfo_left_bt.visibility = View.GONE
            user_info_usericon.setOnClickListener {
                //头像
                Matisse.from(this@UserinfoActivity)
                        .choose(MimeType.ofAll())
                        .countable(true)//是否拍照
                        .captureStrategy(CaptureStrategy(true, "com.burning.emqmsg.Myfileprovider"))//存储到哪里
                        .maxSelectable(8)//最大选择数量
                        // .addFilter(GifSize(320, 320, 5 * Filter.K * Filter.K))过滤器
                        .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .theme(R.style.Matisse_Zhihu)
                        .imageEngine(MatGlideEngine())
                        .forResult(REQUEST_CODE_CHOOSE)
            }
            re_userinfo_gender.setOnClickListener {
                //性别
            }
            re_userinfo_age.setOnClickListener {
                //年龄
            }
            re_userinfo_name.setOnClickListener {
                //昵称
            }
            re_userinfo_ac_reams.setOnClickListener {
                //签名
            }
            bz_userinfo_tv.text = "昵称 :$${findFirst.username}"

        } else {
            val userRemarksPo = realm.where(UserRemarksPo::class.java).equalTo("loginuserPoId", UserInfo.userid).equalTo("userPoId", longExtra).findFirst()
            bz_userinfo_tv.text = "备注 :$${userRemarksPo?.remarksPo}"
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
        particleView.resume()
    }

    override fun onPause() {
        super.onPause()
        particleView.pause()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            val obtainResult = Matisse.obtainResult(data)
            if (obtainResult != null && !obtainResult.isEmpty()) {
                val uriToFile = UriUtils.uriToFile(obtainResult[0], this@UserinfoActivity)
                if (uriToFile != null)
                    Glide.with(this@UserinfoActivity).load(uriToFile).into(user_info_usericon)
            }
        }
    }

}
