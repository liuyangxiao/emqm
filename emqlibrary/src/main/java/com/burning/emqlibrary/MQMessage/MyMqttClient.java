package com.burning.emqlibrary.MQMessage;

import com.burning.emqlibrary.MQMessage.mqimpl.ConnectFactoryImpl;
import com.burning.emqlibrary.MQMessage.mqimpl.MQquenMsg;
import com.burning.emqlibrary.MQMessage.mqimpl.MessageFactoryImpl;

/**
 * Created by burning on 2018/9/6.
 */
public class MyMqttClient {
    public MyMqttClient() {
    }

    Connection connection;

    public void crearConnect() throws Exception {
        MessageFactory messageFactory = new MessageFactoryImpl();
        connection = messageFactory.creatdefMQConnection();
    }

    public void sendMessage(String topic, String message) {
        connection.sendMessage(topic, message);
    }

    public void crearConnect(String clientID) throws Exception {
        ConnectFactory connectFactory = new ConnectFactoryImpl();
        MQConfig mqConfig = new MQConfig();
        mqConfig.setCLIENT_ID(clientID);
        connectFactory.setMqConfig(mqConfig);
        MessageFactory messageFactory = new MessageFactoryImpl();
        connection = messageFactory.creatMQConnection(connectFactory);
    }

    public void addMessageListenr(MQquenMsg mQquenMsg) {
        connection.addTopic(mQquenMsg);
    }

    public void disconnection() {
        if (connection != null)
            connection.disconnection();
        connection = null;
    }

    public void addMessgeLister(MestopicListener meslist) {
        connection.addMessgeLister(meslist);
    }
}
