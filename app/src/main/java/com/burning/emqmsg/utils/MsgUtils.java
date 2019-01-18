package com.burning.emqmsg.utils;

import com.burning.emqlibrary.MQMessage.MessBean;
import com.burning.emqlibrary.emqNet.EmqClientImp;
import com.burning.emqlibrary.emqNet.MqCallBack;
import com.burning.realmdatalibrary.po.MessagePo;
import com.burning.realmdatalibrary.redao.RealmTrasCall;
import com.burning.realmdatalibrary.redao.RxReamlUtils;
import com.google.gson.Gson;

import io.realm.Realm;

/**
 * Created by burning on 2019/1/16.
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
public class MsgUtils {
    public static void sendMessage(final MessagePo messagePo) {
        RxReamlUtils.updata(new RealmTrasCall() {
            @Override
            public void call(Realm realm) {
                String sjson = new Gson().toJson(messagePo);
                realm.createOrUpdateObjectFromJson(MessagePo.class, sjson);
            }
        });
        MqCallBack mqCallBack = new MqCallBack() {
            @Override
            public void onSuccess() {
                RxReamlUtils.updata(new RealmTrasCall() {
                    @Override
                    public void call(Realm realm) {
                        MessagePo first = realm.where(MessagePo.class).equalTo("uuid", messagePo.getUuid()).findFirst();
                        if (first != null) {
                            first.setStatus(0);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {
                RxReamlUtils.updata(new RealmTrasCall() {
                    @Override
                    public void call(Realm realm) {
                        MessagePo first = realm.where(MessagePo.class).equalTo("uuid", messagePo.getUuid()).findFirst();
                        if (first != null) {
                            first.setStatus(2);
                        }
                    }
                });
            }
        };
        MessBean messBean = new MessBean();
        messBean.setUuid(messagePo.getUuid());
        messBean.setClientId(messagePo.getClientId());
        messBean.setCode(messagePo.getCode());
        messBean.setContent(messagePo.getContent());
        if (messagePo.getCode() == 100) {
            EmqClientImp.instance().sendMessage(messagePo.getOfclientID(), messBean, mqCallBack);
        } else if (messagePo.getCode() == 111) {
            EmqClientImp.instance().sendGroupMessage(messagePo.getOfclientID(), messBean, mqCallBack);
        }
    }

}
