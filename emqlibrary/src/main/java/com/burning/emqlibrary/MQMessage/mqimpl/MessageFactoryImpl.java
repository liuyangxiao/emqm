package com.burning.emqlibrary.MQMessage.mqimpl;

import com.burning.emqlibrary.MQMessage.ConnectFactory;
import com.burning.emqlibrary.MQMessage.Connection;
import com.burning.emqlibrary.MQMessage.MQConfig;
import com.burning.emqlibrary.MQMessage.MessageFactory;

/**
 * Created by burning on 2018/9/6.
 */
public class MessageFactoryImpl implements MessageFactory {
    Connection mQconnect;

    public MessageFactoryImpl() {
    }

    public MessageFactoryImpl(ConnectFactory connectionFactory) {
        mQconnect = connectionFactory.creatConnect();
        mQconnect.connection();
    }

    @Override
    public Connection creatdefMQConnection() throws Exception {
        ConnectFactory connectFactory = new ConnectFactoryImpl();
        connectFactory.setMqConfig(new MQConfig());
        mQconnect = connectFactory.creatConnect();
        mQconnect.connection();
        return mQconnect;
    }

    @Override
    public Connection creatMQConnection(ConnectFactory connectionFactory) {
        mQconnect = connectionFactory.creatConnect();
        mQconnect.connection();
        return mQconnect;
    }




}
