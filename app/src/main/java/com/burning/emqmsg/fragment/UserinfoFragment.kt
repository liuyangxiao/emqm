package com.burning.emqmsg.fragment

import android.content.Intent
import com.bumptech.glide.Glide
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqmsg.R
import com.burning.emqmsg.activity.BaseActivity
import com.burning.emqmsg.activity.LoginActivity
import com.burning.emqmsg.activity.UserinfoActivity
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

    override fun initData() {
        fragment_back_title.setPadding(0, BaseActivity.actionBarHeight, 0, 0)
        tv_title.text = "User"
        val baseActivity = activity as BaseActivity
        val findFirstAsync = baseActivity.realm.where(UserPo::class.java).equalTo("id", UserInfo.userid).findFirst()

        Glide.with(this).load("http://47.105.169.72/image/M00/00/00/rB-U8lv2cW2AVUvpAAAhrr-Mr6w145.jpg").into(user_fragment_usericon)
        hideloading()
        user_fragment_username.text = if (findFirstAsync?.username == null) {
            "未设置XX"
        } else {
            findFirstAsync?.username
        }
        user_fragment_userreid.text =
                if (findFirstAsync?.userdesc == null) {
                    "暂无签名信息"
                } else {
                    findFirstAsync?.userdesc
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


}

