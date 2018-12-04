package com.burning.emqlibrary.MQMessage;

import com.burning.emqlibrary.MQMessage.mqimpl.MQquenMsg;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.Listener;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burning on 2018/9/6.
 */
public class MQconnect implements Connection {
    CallbackConnection callbackConnection;

    public MQconnect(CallbackConnection callbackConnection) {
        this.callbackConnection = callbackConnection;
    }

    boolean isConnected;

    @Override
    public boolean isConnection() {
        return isConnected;
    }

    private synchronized void readdTopic() {
        if (topicsMess.size() == 0) {
            return;
        }
        int size = topicsMess.size();
        Topic[] retopics = new Topic[size];
        for (int i = 0; i < topicsMess.size(); i++) {
            retopics[i] = new Topic(topicsMess.get(i).getTopic(), QoS.AT_LEAST_ONCE);
        }
        System.out.println("======readdTopic==========");
        callbackConnection.subscribe(retopics, new Callback<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                System.out.println("======readdTopic=onSuccess=========" + new String(bytes));
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("======readdTopic=throwable=========");
            }
        });

    }

    @Override
    public void connection() {
        System.out.println("========connection=========");
        callbackConnection.listener(new Listener() {
            @Override
            public void onConnected() {
                isConnected = true;

                System.out.println("========onConnected=========");
            }

            @Override
            public void onDisconnected() {
                System.out.println("========onDisconnected=========");
                isConnected = false;
            }

            @Override
            public void onPublish(UTF8Buffer utf8Buffer, Buffer buffer, Runnable runnable) {
                String topic = utf8Buffer.toString();
                String mesContent = new String(buffer.toByteArray());
                System.out.println("========onPublish=========" + topic);
                if (mestopicListener != null) {
                    mestopicListener.onMessage(topic, mesContent);
                }
                for (MQquenMsg mQquenMsg : topicsMess) {
                    String[] split = mQquenMsg.getTopic().split("#");
                    if (topic.contains(split[0])) {
                        mQquenMsg.onMessage(mesContent);
                        runnable.run();
                        return;
                    }
                }
                runnable.run();
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("========throwable=========" + throwable);
            }
        });
        callbackConnection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                isConnected = true;
                readdTopic();
            }

            @Override
            public void onFailure(Throwable throwable) {
                isConnected = false;
                System.out.println("========callbackConnection=========" + throwable);

            }
        });

    }

    @Override
    public void disconnection() {
        callbackConnection.resume();
        callbackConnection.disconnect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {

            }

            @Override
            public void onFailure(Throwable value) {

            }
        });
    }


    @Override
    public void addTopic(final MQquenMsg topic) {
        topicsMess.add(topic);
        if (isConnected) {
            callbackConnection.subscribe(new Topic[]{new Topic(topic.getTopic(), QoS.AT_LEAST_ONCE)}, new Callback<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("======onSuccess==========" + topic);
                }


                @Override
                public void onFailure(Throwable throwable) {
                }
            });
        }
    }

    @Override
    public void sendMessage(String topic, String message) {
        callbackConnection.publish(topic, message.getBytes(), QoS.AT_LEAST_ONCE, false, new Callback<Void>() {
             @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    MestopicListener mestopicListener;

    @Override
    public void addMessgeLister(MestopicListener mestopicListener) {
        this.mestopicListener = mestopicListener;
    }


    List<MQquenMsg> topicsMess = new ArrayList<>();

}
