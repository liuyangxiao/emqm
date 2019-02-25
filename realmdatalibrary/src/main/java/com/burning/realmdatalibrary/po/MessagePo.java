package com.burning.realmdatalibrary.po;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

/**
 * Created by burning on 2018/11/29.
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
@Data
public class MessagePo extends RealmObject {

    Long id;
    /**
     * 消息归属
     */
    long ofclientID;
    /**
     * 消息类型
     * 100--110--用户--普通消息
     * 111---121-群消息
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
     * 更改为uui为主键
     */
    @PrimaryKey
    private String uuid;
    /**
     * 发送者 ID
     */
    private long clientId;
    private long createTime;
    /**
     * 发送状态
     * 0-正常到达-发送成功
     * 1-发送中
     * 2-发送失败
     */
    int status = 0;
}
