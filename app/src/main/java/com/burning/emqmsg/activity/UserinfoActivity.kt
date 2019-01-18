package com.burning.emqmsg.activity

import com.burning.emqmsg.R
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.UserPo
import kotlinx.android.synthetic.main.activity_userinfo.*

class UserinfoActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_userinfo

    companion object {
        var USER_ID = "userid"
    }

    override fun init() {
        val longExtra = intent.getLongExtra(USER_ID, 0)
        if (longExtra == 0L) {
            return
        }
        userinfo_sv.setImageView(userinfo_bg_iv)
        userinfo_sv.setOnHeaderRefreshListener {
            1
        }
        val findFirst = realm.where(UserPo::class.java).equalTo("id", longExtra).findFirst()
        user_info_username.text = "${findFirst.username}"
        if (longExtra == UserInfo.userid) {
            user_info_username.text = "自己 : ${findFirst.username}"
        }
        user_info_userreid.text = "私密ID : ${findFirst.setID}"
        userinfo_left_bt.setOnClickListener {

        }
        userinfo_righ_bt.setOnClickListener {

        }

    }


}
