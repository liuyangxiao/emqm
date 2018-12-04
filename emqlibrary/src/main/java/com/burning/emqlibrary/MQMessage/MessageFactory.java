package com.burning.emqlibrary.MQMessage;

/**
 * Created by burning on 2018/9/6.
 */
public interface MessageFactory {

    Connection creatMQConnection(ConnectFactory connectionFactory);

    Connection creatdefMQConnection() throws Exception;
}
