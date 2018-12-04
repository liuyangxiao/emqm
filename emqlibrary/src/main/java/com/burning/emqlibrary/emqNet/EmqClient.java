package com.burning.emqlibrary.emqNet;

import com.burning.emqlibrary.MQMessage.MessBean;

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
public interface EmqClient {
    /**
     * @param uid             --接收方ID
     * @param messBean---消息主体
     */
    void sendMessage(Long uid, MessBean messBean, MqCallBack mqCallBack);

    void sendGroupMessage(Long groupID, MessBean messBean, MqCallBack mqCallBack);

    void setListen(MqListen mqListen);

    /**
     * 链接状态
     *
     * @return
     */
    boolean isConnect();

    /**
     * 取消链接
     */
    void disconnect();

    /**
     * 重新链接
     */
    void connect();


    void killed();
}
