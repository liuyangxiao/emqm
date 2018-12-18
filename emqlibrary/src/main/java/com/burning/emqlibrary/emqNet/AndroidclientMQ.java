package com.burning.emqlibrary.emqNet;

import android.content.Context;

import com.burning.emqlibrary.MQMessage.MessBean;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by burning on 2018/12/18.
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
public class AndroidclientMQ implements EmqClient {
    MqttAndroidClient androidClient;

    @Override
    public void sendMessage(Long uid, MessBean messBean, MqCallBack mqCallBack) {


    }

    @Override
    public void sendGroupMessage(Long groupID, MessBean messBean, MqCallBack mqCallBack) {

    }

    @Override
    public void setListen(MqListen mqListen) {

    }

    @Override
    public boolean isConnect() {
        return false;
    }

    @Override
    public void disconnect() {
        Context context = null;
        androidClient = new MqttAndroidClient(context, "", "");
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//        mqttConnectOptions.s
        androidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }


    @Override
    public void connect() {

    }

    @Override
    public void killed() {

    }
}
