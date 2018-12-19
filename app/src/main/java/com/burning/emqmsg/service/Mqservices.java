package com.burning.emqmsg.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.burning.emqlibrary.MQMessage.MessBean;
import com.burning.emqlibrary.emqNet.EmqClient;
import com.burning.emqlibrary.emqNet.EmqClientImp;
import com.burning.emqlibrary.emqNet.MqListen;
import com.orhanobut.logger.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class Mqservices extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    EmqClient instance;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("========onStartCommand=========================");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Logger.d("========onStartCommand========run======");
                if (instance == null) {
                    try {
                        instance = EmqClientImp.instance();
                        instance.setListen(mqListen);
                    } catch (Exception e) {
                    }
                }
                instance.connect();
            }
        });
        return super.onStartCommand(intent, flags, startId);

    }

    MqListen mqListen = new MqListen() {
        @Override
        public void onMessage(MessBean messBean) {
            //普通消息
            Logger.d("========onMessage==============");
        }

        @Override
        public void onGroupMessage(Long groupid, MessBean messBean) {
            //群消息
            Logger.d("========onGroupMessage==============");
        }

        @Override
        public void onServiceMessage(MessBean messBean) {
            //系统消息
            Logger.d("========onServiceMessage==============");
        }

        @Override
        public void onConnected() {
            // 链接成功
            Logger.d("========onConnected==============");
        }

        @Override
        public void onDisconnected() {
            // 断开链接
            Logger.d("========onDisconnected==============");
            //检测未链接  5秒后重连
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance.connect();

        }
    };
}
