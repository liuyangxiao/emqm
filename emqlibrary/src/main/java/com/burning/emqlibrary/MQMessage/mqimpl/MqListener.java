package com.burning.emqlibrary.MQMessage.mqimpl;

import com.burning.emqlibrary.MQMessage.MessageListener;

/**
 * Created by burning on 2018/9/10.
 */
public class MqListener implements MessageListener {


    @Override
    public void onMessage(String message) {
        System.out.println("========onMessage======" + message);
    }
}
