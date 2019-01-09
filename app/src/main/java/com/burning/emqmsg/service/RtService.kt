package com.burning.emqmsg.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.orhanobut.logger.Logger

class RtService : Service() {
    override fun onCreate() {
        super.onCreate()
        Logger.d("======RtService==onConnected==========ID====" + android.os.Process.myPid())
    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.d("======RtService==onConnected=======ID=======" + android.os.Process.myPid())
        return super.onStartCommand(intent, Service.START_STICKY_COMPATIBILITY, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        Logger.d("======RtService==onDestroy=======ID=======" + android.os.Process.myPid())
        super.onDestroy()
    }
}
