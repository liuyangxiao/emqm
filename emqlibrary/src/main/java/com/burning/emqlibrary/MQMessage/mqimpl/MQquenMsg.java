package com.burning.emqlibrary.MQMessage.mqimpl;

import com.burning.emqlibrary.MQMessage.MessageListener;

/**
 * Created by burning on 2018/9/12.
 */
public class MQquenMsg {
    MessageListener messageListener;
    String topic;

    public MQquenMsg(String topic) {
        this.topic = topic;
    }

    public MQquenMsg(String topic, MessageListener messageListener) {
        this.messageListener = messageListener;
        this.topic = topic;
    }

    public void onMessage(String msg) {
        if (messageListener != null)
            messageListener.onMessage(msg);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
