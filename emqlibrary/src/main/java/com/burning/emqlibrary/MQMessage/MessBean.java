package com.burning.emqlibrary.MQMessage;

import java.io.Serializable;

import lombok.Data;

/**
 * @author burning
 * @date 2018/9/14
 */
@Data
public class MessBean implements Serializable {
    /**
     * 消息的类型
     * 100----普通消息
     * 200----系统消息 好友添加
     * 300---系统消息 添加好友通过
     * 400---系统通知
     */
    int code;
    /**
     * 消息主体
     */
    String content;
    /**
     * 消息 唯一标识
     */
    String uuid;
    /**
     * 发送者 ID
     */
    long clientId;

}
