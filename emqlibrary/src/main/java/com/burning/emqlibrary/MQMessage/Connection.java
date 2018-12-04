package com.burning.emqlibrary.MQMessage;

import com.burning.emqlibrary.MQMessage.mqimpl.MQquenMsg;

/**
 * Created by burning on 2018/9/7.
 */
public interface Connection {
    boolean isConnection();

    void connection();
    void disconnection();
    void addTopic(MQquenMsg topic);

    void sendMessage(String topic, String message);

    void addMessgeLister(MestopicListener messagelis);
}
