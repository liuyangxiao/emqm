package com.burning.emqmsg.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import com.burning.emqlibrary.MQMessage.MessBean
import com.burning.emqlibrary.emqNet.EmqClient
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqlibrary.emqNet.MqListen
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.MesgWinPo
import com.burning.realmdatalibrary.po.MessagePo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import com.orhanobut.logger.Logger
import java.util.concurrent.Executors

/**
 * Created by burning on 2018/11/30.
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * -------------------------//┏┓　　　┏┓
 * -------------------------//┏┛┻━━━┛┻┓
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　━　　　┃
 * -------------------------//┃　┳┛　┗┳　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　┻　　　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┗━┓　　　┏━┛
 * -------------------------//┃　　　┃  神兽保佑
 * -------------------------//┃　　　┃  代码无BUG！
 * -------------------------//┃　　　┗━━━┓
 * -------------------------//┃　　　　　　　┣┓
 * -------------------------//┃　　　　　　　┏┛
 * -------------------------//┗┓┓┏━┳┓┏┛
 * -------------------------// ┃┫┫　┃┫┫
 * -------------------------// ┗┻┛　┗┻┛
 */
class Mqservices : Service() {

    internal var instance: EmqClient? = null
    var executorService = Executors.newSingleThreadExecutor()


    var mqListen: MqListen = object : MqListen {
        override fun onMessage(messBean: MessBean) {

            //普通消息
            Logger.d("========onMessage==============")
            RxReamlUtils.updata {
                val createObject = it.createObject(MessagePo::class.java, messBean.uuid)
                createObject.content = messBean.content
                createObject.clientId = messBean.clientId
                createObject.code = messBean.code
                createObject.ofclientID = UserInfo.userid
                // createObject.uuid =
                createObject.createTime = System.currentTimeMillis()
                //消息聊天窗口--若不存在 则添加
                if (it.where(MesgWinPo::class.java).equalTo("userid", UserInfo.userid).equalTo("type", 2).equalTo("msgid", messBean.clientId).findFirst() == null) {
                    val createObject = it.createObject(MesgWinPo::class.java)
                    createObject.msgid = messBean.clientId
                    createObject.userid = UserInfo.userid
                    createObject.type = 1
                }


                //createObject.id = 332
            }

        }

        override fun onGroupMessage(groupid: Long?, messBean: MessBean) {
            //群消息
            RxReamlUtils.updata {
                //消息聊天窗口--若不存在 则添加
                if (it.where(MesgWinPo::class.java).equalTo("userid", UserInfo.userid).equalTo("type", 2).equalTo("msgid", groupid).findFirst() == null) {
                    val createObject = it.createObject(MesgWinPo::class.java)
                    createObject.msgid = messBean.clientId
                    createObject.userid = UserInfo.userid
                    createObject.type = 2
                }
                if (messBean.clientId == UserInfo.userid) {
                    //自己发送的消息-不保存
                    return@updata
                }
                val createObject = it.createObject(MessagePo::class.java, messBean.uuid)
                createObject.content = messBean.content
                createObject.clientId = messBean.clientId
                createObject.code = messBean.code
                createObject.ofclientID = groupid!!
                // createObject.uuid = messBean.uuid
                createObject.createTime = System.currentTimeMillis()
                //createObject.id = 332
            }
        }

        override fun onServiceMessage(messBean: MessBean) {
            //系统消息
            Logger.d("========onServiceMessage==============")
        }

        override fun onConnected() {
            // 链接成功
            Logger.d("========onConnected==============")
            intconnect = 1//恢复 链接时间为  1
            connecttime = 1000L
        }

        override fun onDisconnected() {
            // 断开链接
            Logger.d("========onDisconnected==============")
            //检测未链接  5秒后重连
            if (intconnect > 10) {
                return
            }
            try {//待完成 重连规则
                Thread.sleep(connecttime * intconnect * intconnect)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            instance?.connect()
        }
    }
    var intconnect = 1
    var connecttime = 1000L
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        Logger.d("========onStartCommand=========================")

        //  Toast.makeText(getApplicationContext(), "onStartCommand", Toast.LENGTH_SHORT).show()
        executorService.execute {
            Logger.d("========onStartCommand========run======")
            instance = EmqClientImp.instance()
            instance!!.setListen(mqListen)
            instance!!.connect()
        }
        startForeground(2213, Notification())
        return super.onStartCommand(intent, START_REDELIVER_INTENT, startId)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDestroy() {
        super.onDestroy()
        startForegroundService(Intent(applicationContext, Mqservices::class.java))
    }
}
