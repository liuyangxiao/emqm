package com.burning.emqlibrary.MQMessage;

/**
 * Created by burning on 2018/9/7.
 */
public class MqMessge {
    String topic;
    String message;
    /**
     * 此消息 是否永久保持在service 以便于 现在未订阅 用户获取 似乎仅最后一条意义不大
     */
    boolean retain = false;


}
