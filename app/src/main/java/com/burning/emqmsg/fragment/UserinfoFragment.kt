package com.burning.emqmsg.fragment

import android.content.Intent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.LoginActivity
import com.burning.emqmsg.activity.UserinfoActivity
import com.burning.emqmsg.glidehelp.MyTransform
import com.burning.emqmsg.utils.ImageConfig
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.LoginsPo
import com.burning.realmdatalibrary.po.UserPo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import kotlinx.android.synthetic.main.back_title.*
import kotlinx.android.synthetic.main.fragment_userinfo.*

/**
 * Created by burning on 2018/10/23.
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
-------------------------//┏┓　　　┏┓
-------------------------//┏┛┻━━━┛┻┓
-------------------------//┃　　　　　　　┃ 　
-------------------------//┃　　　━　　　┃
-------------------------//┃　┳┛　┗┳　┃
-------------------------//┃　　　　　　　┃
-------------------------//┃　　　┻　　　┃
-------------------------//┃　　　　　　　┃
-------------------------//┗━┓　　　┏━┛
-------------------------//┃　　　┃  神兽保佑　　　　　　　　
-------------------------//┃　　　┃  代码无BUG！
-------------------------//┃　　　┗━━━┓
-------------------------//┃　　　　　　　┣┓
-------------------------//┃　　　　　　　┏┛
-------------------------//┗┓┓┏━┳┓┏┛
-------------------------// ┃┫┫　┃┫┫
-------------------------// ┗┻┛　┗┻┛
 */
class UserinfoFragment : BaseFragment() {
    override fun initViewOnlayout(): Int = R.layout.fragment_userinfo
    var userpo: UserPo? = null
    override fun initData() {
        fragment_back_title.setPadding(0, BaseActivity.actionBarHeight, 0, 0)
        tv_title.text = "User"
        val baseActivity = activity as BaseActivity
        userpo = baseActivity.realm.where(UserPo::class.java).equalTo("id", UserInfo.userid).findFirst()
        setUser(userpo!!)
        userpo?.addChangeListener<UserPo> {
            setUser(it)
        }
        userinfo_fg_loginout.setOnClickListener {
            UserInfo.userid = 0L
            EmqClientImp.instance().upsubtopicks(null)
            RxReamlUtils.updata { realm ->
                val findFirst = realm.where(LoginsPo::class.java).equalTo("status", 100).findFirst()
                findFirst.status = 200
                baseActivity.runOnUiThread {
                    startActivity(Intent(activity, LoginActivity::class.java))
                    activity?.finish()
                }
            }
        }
        re_fg_setuser.setOnClickListener {
            var intent = Intent(activity, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, UserInfo.userid)
            startActivity(intent)
        }
    }

    fun setUser(userPo: UserPo) {
        Glide.with(this).load(ImageConfig.Image_path + userPo.icon).apply(MyTransform.getCircleCrop()).into(user_fragment_usericon as ImageView)
        hideloading()
        user_fragment_username.text = if (userPo?.username == null) {
            "未设置XX"
        } else {
            userPo?.username
        }
        user_fragment_userreid.text =
                if (userPo?.userdesc == null) {
                    "暂无签名信息"
                } else {
                    userPo?.userdesc
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        userpo?.removeAllChangeListeners()
    }
}

