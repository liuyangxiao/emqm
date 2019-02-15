package com.burning.emqmsg.activity

import android.widget.ArrayAdapter
import com.burning.emqmsg.R
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.GroupPo
import com.burning.realmdatalibrary.po.UserPo
import kotlinx.android.synthetic.main.activity_add_frend2.*
import kotlinx.android.synthetic.main.back_title.*


class AddFrendActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_add_frend2

    companion object {
        var USER_ID = "USER_ID"
    }

    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        val longExtra = intent.getLongExtra(USER_ID, 0)
        val findFirst = realm.where(UserPo::class.java).equalTo("id", longExtra).findFirst()
        tv_title.text = "好友添加"
        user_fragment_username.text = findFirst.username
        //好友分组列表
        val findAll = realm.where(GroupPo::class.java).equalTo("groupid", UserInfo.userid).equalTo("type", 1).findAll();
        var data = ArrayList<String>();
        data.add("xxxx")
        data.add("xxx11x")
        data.add("xx22xx")
        data.add("xxx333x")
        sp_activity_userinfo_iv_data.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)
        send_addfrend.setOnClickListener {

        }
    }
}
