package com.burning.emqmsg.activity

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.GroupImageAdapter
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.LoginUserPo
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.back_title.*

/**
 * Created by burning on 2019/1/10.
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
class GroupActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_group

    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)

        tv_title.text = "群聊"
        activity_group_rv.layoutManager = LinearLayoutManager(this)
        var data = realm.where(LoginUserPo::class.java).equalTo("userid", UserInfo.userid).findFirst().groupPos.where().equalTo("type", 2).findAll()
        activity_group_rv.adapter = GroupImageAdapter(this, data)
        iv_right.text = "添加"
        iv_right.setOnClickListener {

        }
    }

}