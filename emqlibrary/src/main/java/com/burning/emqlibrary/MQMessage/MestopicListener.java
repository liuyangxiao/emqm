package com.burning.emqlibrary.MQMessage;

/**
 * Created by burning on 2018/9/10.
 */
public interface MestopicListener {

    void onMessage(String topic, String message);
}
