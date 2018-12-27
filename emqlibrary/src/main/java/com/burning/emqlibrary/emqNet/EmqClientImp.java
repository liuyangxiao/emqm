package com.burning.emqlibrary.emqNet;

import com.burning.emqlibrary.MQMessage.MQConfig;
import com.burning.emqlibrary.MQMessage.MessBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by burning on 2018/11/30.
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
public class EmqClientImp implements EmqClient {
    private EmqClientImp() throws Exception {

        setMqConfig(new MQConfig());
    }

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void sendMessage(Long uid, MessBean messBean, MqCallBack mqCallBack) {

        String message = new Gson().toJson(messBean);
        senMessage(TopicHelp.baseToppic + uid, message, mqCallBack);
    }

    @Override
    public void sendGroupMessage(Long groupID, MessBean messBean, MqCallBack mqCallBack) {
        String message = new Gson().toJson(messBean);
        senMessage(TopicHelp.baseGroupToppic + groupID, message, mqCallBack);
    }

    private void senMessage(final String topice, final String message, final MqCallBack mqCallBack) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                callbackConnection.publish(topice, message.getBytes(), QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        if (mqCallBack != null)
                            mqCallBack.onSuccess();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        if (mqCallBack != null)
                            mqCallBack.onFailure(throwable);
                    }
                });

            }
        });
    }


    @Override
    public void setListen(MqListen mqListen) {
        this.mqListen = mqListen;
        if (isConnect()) {
            mqListen.onConnected();
        }
    }

    boolean connect = false;

    @Override
    public boolean isConnect() {
        return connect;
    }

    private void subtopick(Topic[] topics) {
        if (!isConnect()) {
            connect();
            return;
        }
        if (topics == null || topics.length == 0 || callbackConnection == null)
            return;
        callbackConnection.subscribe(topics/*{new Topic(TopicHelp.serviceToppic + Userinfo, QoS.AT_LEAST_ONCE)}*/, new Callback<byte[]>() {
            @Override
            public void onSuccess(byte[] value) {
                Logger.d("========订阅 成功==============");
            }

            @Override
            public void onFailure(Throwable value) {
                if (value.getMessage().contains("isconnected")) {
                    //订阅显示---未链接--但判断显示已链接  则重新获取一条链接
                    //赋值 未链接
                    connect = false;
                    callbackConnection = mqtt.callbackConnection();
                }
                //重新开启链接
                connect();
                Logger.d("========订阅 失败 重连==============");
                // disconnect();

            }
        });
    }

    @Override
    public void disconnect() {
        if (callbackConnection != null && connect)
            callbackConnection.disconnect(new Callback<Void>() {
                @Override
                public void onSuccess(Void value) {
                    connect = false;
                }

                @Override
                public void onFailure(Throwable value) {
                    connect = false;
                }
            });
    }

    @Override
    public void connect() {
       /* if (isConnect())
            return;
        if (callbackConnection != null) {
            disconnect();
            callbackConnection = null;
        }
        callbackConnection = mqtt.callbackConnection();
        */
        if (callbackConnection == null) {
            callbackConnection = mqtt.callbackConnection();
            callbackConnection.listener(mqclientListent);
        }
        if (isConnect())
            return;//已经连接则不链接
        callbackConnection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                Logger.d("========onSuccess==============");
            }

            @Override
            public void onFailure(Throwable value) {
                if (!isConnect() && value.getMessage().equals("Already connected")) {
                    //本地校验未链接 但 mq链接却显示已链接--链接异常
                    //测试先修改为 链接正常
                    connect = true;
                  /*  callbackConnection = mqtt.callbackConnection();//重新 再获取一个连接发起重连
                    connect();*/
                }
                Logger.d("======connect==onFailure==============");
            }
        });
    }

    private static EmqClient emqClient;

    public synchronized static EmqClient instance() throws Exception {
        if (emqClient == null)
            emqClient = new EmqClientImp();
        return emqClient;
    }

    @Override
    public void killed() {
        callbackConnection.kill(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {

            }

            @Override
            public void onFailure(Throwable value) {

            }
        });
    }

    Set<Topic> datatopic = new HashSet<>();

    @Override
    public void addtopick(String topick) {
        Topic topic = new Topic(topick, QoS.AT_LEAST_ONCE);
        datatopic.add(topic);
        if (callbackConnection != null && isConnect()) {
            //  Topic[] objects = (Topic[]) datatopic.toArray();
            subtopick(new Topic[]{topic});
        }
    }

    @Override
    public void addtopicks(Set<String> topick) {
        Iterator<String> iterator = topick.iterator();
        while (iterator.hasNext()) {
            Topic topic = new Topic(iterator.next(), QoS.AT_LEAST_ONCE);
            datatopic.add(topic);
        }
        subtopick(datatopic.toArray(new Topic[datatopic.size()]));
    }

    CallbackConnection callbackConnection;
    MQTT mqtt;

    private void setMqConfig(MQConfig mqConfig) throws Exception {
        Logger.d("========setMqConfig==============");
        if (mqConfig == null) {
            return;
        }
        mqtt = new MQTT();
        mqtt.setHost(mqConfig.getHost());
        mqtt.setCleanSession(mqConfig.isCleanSession());
        mqtt.setKeepAlive(mqConfig.getKEEP_ALIVE());
        mqtt.setClientId(mqConfig.getCLIENT_ID());
        mqtt.setReconnectAttemptsMax(mqConfig.getRECONNECTION_ATTEMPT_MAX());
        mqtt.setReconnectDelay(mqConfig.getRECONNECTION_DELAY());
        mqtt.setSendBufferSize(mqConfig.getSEND_BUFFER_SIZE());
        mqtt.setTrafficClass(8);
        mqtt.setUserName(mqConfig.getUserName());
        mqtt.setPassword(mqConfig.getPassWord());
        mqtt.setMaxReadRate(0);
        mqtt.setMaxWriteRate(0);

    }

    MqListen mqListen;
    Listener mqclientListent = new Listener() {
        @Override
        public void onConnected() {
            connect = true;
            //添加上订阅
            if (mqListen != null)
                mqListen.onConnected();
            subtopick(datatopic.toArray(new Topic[datatopic.size()]));
        }

        @Override
        public void onDisconnected() {
            connect = false;
            if (mqListen != null)
                mqListen.onDisconnected();
        }

        @Override
        public void onPublish(UTF8Buffer topic, Buffer body, Runnable ack) {
            if (mqListen == null) {
                ack.run();
                return;
            }
            String msg = topic.toString();
            String mesContent = new String(body.toByteArray());
            MessBean messBean;
            try {
                messBean = new Gson().fromJson(mesContent, MessBean.class);
            } catch (Exception e) {
                Logger.d("========onPublish=======Exception=======" + e);
                ack.run();
                return;
            }

            if (msg.startsWith(TopicHelp.baseGroupToppic)) {
                //群消息
                String replace = msg.replace(TopicHelp.baseGroupToppic, "");
                mqListen.onGroupMessage(Long.valueOf(replace), messBean);
            } else if (msg.startsWith(TopicHelp.serviceToppic)) {
                //系统消息
                mqListen.onServiceMessage(messBean);
            } else {
                //单聊消息
                mqListen.onMessage(messBean);
            }
            ack.run();
        }

        @Override
        public void onFailure(Throwable value) {

            connect = false;
            Logger.d("========onFailure==============");
        }
    };


}
