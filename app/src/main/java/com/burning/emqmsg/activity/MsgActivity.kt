package com.burning.emqmsg.activity

import android.support.v7.widget.LinearLayoutManager
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.MsgActivityAdapter
import kotlinx.android.synthetic.main.activity_msg.*

/**
 * Created by burning on 2018/10/29.
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
class MsgActivity : BaseActivity() {
    override fun getActivityLayout(): Int = R.layout.activity_msg

    companion object {
        var USER_ID = "USER_ID"
    }

    override fun init() {
        val userid = intent.getLongExtra(USER_ID, 0)
       //cto_view.setViewById(to_test)
        activity_msg_recview.layoutManager = LinearLayoutManager(this)
        var data = ArrayList<String>()
        data.add("")
        data.add("")
        data.add("")
        data.add("")
        data.add("")
        data.add("")
        data.add("")
        data.add("")
        data.add("")
        activity_msg_recview.adapter = MsgActivityAdapter(this, data)
   //    var  span= ImageSpan()
       // activity_msg_content.append()
    }
}