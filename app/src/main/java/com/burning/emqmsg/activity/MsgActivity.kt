package com.burning.emqmsg.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.burning.emqmsg.R
import com.burning.emqmsg.adapter.MsgActivityAdapter
import com.burning.emqmsg.utils.MsgUtils
import com.burning.emqmsg.view.SoftHideKeyBoardUtil
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.GroupPo
import com.burning.realmdatalibrary.po.MesgWinPo
import com.burning.realmdatalibrary.po.MessagePo
import com.burning.realmdatalibrary.po.UserPo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_msg.*
import kotlinx.android.synthetic.main.back_title.*
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
        var GROUP_ID = "Gro_ID"
    }

    var code = 100
    var msgid = 0L
    override fun init() {

        SoftHideKeyBoardUtil.assistActivity(this).setOnWindow {
            activity_msg_recview.apply {
                post {
                    scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
        title_layout.setPadding(title_layout.left, title_layout.top + BaseActivity.actionBarHeight, title_layout.right, title_layout.bottom)
        msgid = intent.getLongExtra(USER_ID, 0)
        if (msgid == UserInfo.userid) {
            var intent = Intent(this@MsgActivity, UserinfoActivity::class.java)
            intent.putExtra(UserinfoActivity.USER_ID, msgid)
            startActivity(intent)
            finish()
            return
        }
        var result = if (0L != msgid) {
            //单消息
            code = 100
            val userPo = realm.where(UserPo::class.java).equalTo("id", msgid).findFirst()
            tv_title.text = userPo.username
            RxReamlUtils.updata {
                if (it.where(MesgWinPo::class.java).equalTo("userid", UserInfo.userid).equalTo("type", 1).equalTo("msgid", msgid).findFirst() == null) {
                    val createObject = it.createObject(MesgWinPo::class.java)
                    createObject.msgid = msgid
                    createObject.userid = UserInfo.userid
                    createObject.type = 1
                }
            }
            realm.where(MessagePo::class.java)
                    .beginGroup()
                    .equalTo("code", code)
                    .equalTo("ofclientID", UserInfo.userid).equalTo("clientId", msgid).endGroup()
                    .or()
                    .equalTo("code", code)
                    .beginGroup().equalTo("clientId", UserInfo.userid).equalTo("ofclientID", msgid).endGroup()
                    .findAllSortedAsync("createTime", Sort.ASCENDING)
            //正序
        } else {
            //群--
            code = 111
            msgid = intent.getLongExtra(GROUP_ID, 0)
            var mGroupPo = realm.where(GroupPo::class.java).equalTo("id", msgid).findFirst()
            tv_title.text = mGroupPo.content
            RxReamlUtils.updata {
                if (it.where(MesgWinPo::class.java).equalTo("userid", UserInfo.userid).equalTo("type", 2).equalTo("msgid", msgid).findFirst() == null) {
                    val createObject = it.createObject(MesgWinPo::class.java)
                    createObject.msgid = msgid
                    createObject.userid = UserInfo.userid
                    createObject.type = 2
                }
            }

            realm.where(MessagePo::class.java)
                    .equalTo("code", code)
                    .equalTo("ofclientID", msgid).findAllSortedAsync("createTime", Sort.ASCENDING)
        }
        result.addChangeListener { results ->
            activity_msg_recview.apply {
                if (adapter == null) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = MsgActivityAdapter(context, results)
                } else {
                    adapter.notifyItemChanged(results.size)
                }
                if (results.size != 0) {
                    post {
                        scrollToPosition(adapter.itemCount - 1)
                    }
                }
            }
        }
        activity_msg_send.setOnClickListener {
            if (TextUtils.isEmpty(activity_msg_content.text))
                return@setOnClickListener
            //消息发送
            var messagePo = MessagePo()
            messagePo.createTime = System.currentTimeMillis()
            messagePo.code = code
            messagePo.clientId = UserInfo.userid
            messagePo.ofclientID = msgid
            messagePo.content = activity_msg_content.text.toString()
            messagePo.status = 1
            messagePo.uuid = UUID.randomUUID().toString()
            MsgUtils.sendMessage(messagePo)
            runOnUiThread {
                activity_msg_content.text = null
            }
        }

    }
}