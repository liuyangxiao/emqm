package com.burning.emqlibrary.MQMessage.mqimpl;

import com.burning.emqlibrary.MQMessage.ConnectFactory;
import com.burning.emqlibrary.MQMessage.MQConfig;
import com.burning.emqlibrary.MQMessage.MQconnect;

import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;


/**
 * Created by burning on 2018/9/10.
 */
public class ConnectFactoryImpl implements ConnectFactory {
    @Override
    public MQconnect creatConnect(String name, String passWord) throws Exception {
        MQConfig mqConfig = new MQConfig();
        mqConfig.setUserName(name);
        mqConfig.setPassWord(passWord);
        setMqConfig(mqConfig);
        return creatConnect();
    }


    MQTT mqtt;

    @Override
    public void setMqConfig(MQConfig mqConfig) throws Exception {
        System.out.println("-------------------");
        if (mqConfig == null) {
            return;
        }
        mqtt = new MQTT();
        mqtt.setHost(mqConfig.getHost());
        mqtt.setCleanSession(mqConfig.isCleanSession());
        mqtt.setKeepAlive(mqConfig.getKEEP_ALIVE());
        mqtt.setClientId(mqConfig.getCLIENT_ID());
        mqtt.setReconnectAttemptsMax(mqConfig.getRECONNECTION_ATTEMPT_MAX());
        mqtt.setReconnectDelay(mqConfig.getRECONNECTION_DELAY());
        mqtt.setSendBufferSize(mqConfig.getSEND_BUFFER_SIZE());
        mqtt.setTrafficClass(8);
        mqtt.setUserName(mqConfig.getUserName());
        mqtt.setPassword(mqConfig.getPassWord());
        mqtt.setMaxReadRate(0);
        mqtt.setMaxWriteRate(0);
    }


    @Override
    public MQconnect creatConnect() {
        if (mqtt == null) {
            return null;
        }
        CallbackConnection callbackConnection = mqtt.callbackConnection();
        return new MQconnect(callbackConnection);
    }

}
