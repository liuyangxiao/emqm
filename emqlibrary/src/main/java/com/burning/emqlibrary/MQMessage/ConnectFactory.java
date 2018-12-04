package com.burning.emqlibrary.MQMessage;

import java.net.URISyntaxException;

/**
 * Created by burning on 2018/9/6.
 */
public interface ConnectFactory {
    MQconnect creatConnect(String name, String passWord) throws Exception;

    void setMqConfig(MQConfig mqConfig) throws URISyntaxException, Exception;

    Connection creatConnect();
}
