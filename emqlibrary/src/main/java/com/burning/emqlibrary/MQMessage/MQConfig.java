package com.burning.emqlibrary.MQMessage;

import java.util.UUID;

import lombok.Data;

/**
 * Created by burning on 2018/9/10.
 */
@Data
public class MQConfig {
    String host = "tcp://47.105.169.72:1883";
    /**
     * 是否持久化 消息
     */
    boolean cleanSession = true;
    short KEEP_ALIVE = 30;
    String userName = "admin";
    String passWord = "public";
    String CLIENT_ID = UUID.randomUUID().toString();
    /**
     * 重连上限
     */
    long RECONNECTION_ATTEMPT_MAX = 1;
    /**
     * 每次重连时间间隔
     */
    long RECONNECTION_DELAY = 10;
    /**
     * 缓存--数据大小
     */
    int SEND_BUFFER_SIZE = 65535;
}
