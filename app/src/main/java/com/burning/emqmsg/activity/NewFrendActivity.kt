package com.burning.emqmsg.activity

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.NewFrendAdapter
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
class NewFrendActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_group

    override fun init() {
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)

        tv_title.text = "好友请求"
        activity_group_rv.layoutManager = LinearLayoutManager(this)
        activity_group_rv.adapter = NewFrendAdapter(this, ArrayList())
    }

}