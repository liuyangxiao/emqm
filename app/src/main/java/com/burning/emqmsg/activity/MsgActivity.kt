package com.burning.emqmsg.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import com.burning.emqlibrary.MQMessage.MessBean
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqlibrary.emqNet.MqCallBack
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.MsgActivityAdapter
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.MessagePo
import com.burning.realmdatalibrary.po.UserPo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_msg.*
import kotlinx.android.synthetic.main.back_title.*
import kotlinx.android.synthetic.main.fragmeng_frend.*
import java.util.*

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
        tv_title.text = realm.where(UserPo::class.java).equalTo("id", userid).findFirst().username
        realm.where(MessagePo::class.java)
                .beginGroup().equalTo("ofclientID", UserInfo.userid).equalTo("clientId", userid).endGroup()
                .or()
                .beginGroup().equalTo("clientId", UserInfo.userid).equalTo("ofclientID", userid).endGroup()
                .findAllSortedAsync("id", Sort.ASCENDING)//正序
                .addChangeListener { results ->
                    activity_msg_recview.apply {
                        if (adapter == null) {
                            layoutManager = LinearLayoutManager(context)
                            adapter = MsgActivityAdapter(context, results)
                        } else {
                            adapter.notifyDataSetChanged()
                        }
                        post {
                            scrollToPosition(adapter.itemCount - 1)
                        }
                    }
                }
        activity_msg_send.setOnClickListener {
            //伪装成二号 发送给自己
            var msg = MessBean()
            msg.clientId = 2 //UserInfo.userid 伪装为ID2用户发送消息
            msg.code = 100
            msg.content = "测试发送消息TTT" + Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH) +
                    activity_msg_content.text
            msg.uuid = "xxxxxx2341" + Random().nextInt(100000)
            EmqClientImp.instance().sendMessage(
                    1,// ---发送给自己
                    msg,
                    object : MqCallBack {
                        override fun onSuccess() {
                            print("================OK")
                            runOnUiThread {
                                activity_msg_content.text = null
                            }
                        }

                        override fun onFailure(throwable: Throwable?) {
                            if (throwable != null) {
                                print("================onFailure" + throwable.message)
                                runOnUiThread {
                                    activity_msg_content.text = Editable.Factory.getInstance().newEditable("发送失败")
                                }

                            }
                        }

                    }
            )
        }
    }
}