package com.burning.emqmsg.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

import com.burning.emqlibrary.MQMessage.MessBean
import com.burning.emqlibrary.emqNet.EmqClient
import com.burning.emqlibrary.emqNet.EmqClientImp
import com.burning.emqlibrary.emqNet.MqListen
import com.burning.realmdatalibrary.UserInfo
import com.burning.realmdatalibrary.po.MessagePo
import com.burning.realmdatalibrary.redao.RxReamlUtils
import com.orhanobut.logger.Logger

import java.util.concurrent.ExecutorService
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
                val createObject = it.createObject(MessagePo::class.java)
                createObject.content = messBean.content
                createObject.clientId = messBean.clientId
                createObject.code = messBean.code
                createObject.ofclientID = UserInfo.userid
                createObject.uuid = messBean.uuid
                createObject.createTime = System.currentTimeMillis()
                createObject.id = 332
            }

        }

        override fun onGroupMessage(groupid: Long?, messBean: MessBean) {
            //群消息
            Logger.d("========onGroupMessage==============")
        }

        override fun onServiceMessage(messBean: MessBean) {
            //系统消息
            Logger.d("========onServiceMessage==============")
        }

        override fun onConnected() {
            // 链接成功
            Logger.d("========onConnected==============")
        }

        override fun onDisconnected() {
            // 断开链接
            Logger.d("========onDisconnected==============")
            //检测未链接  5秒后重连
            try {//待完成 重连规则
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            instance!!.connect()

        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
//        Logger.d("========onStartCommand=========================")

        Toast.makeText(getApplicationContext(), "onStartCommand", Toast.LENGTH_SHORT).show();
        executorService.execute {
            Logger.d("========onStartCommand========run======")
            instance = EmqClientImp.instance()
            instance!!.setListen(mqListen)
            instance!!.connect()
        }
        startForeground(2213, Notification())
        return super.onStartCommand(intent, flags, startId)

    }
}
