package com.burning.emqmsg;

import android.app.Application;
import android.content.Intent;

import com.burning.emqmsg.service.Mqservices;
import com.burning.mybaselibrary.LogUtils;
import com.burning.realmdatalibrary.po.MessagePo;
import com.burning.realmdatalibrary.po.UserPo;
import com.burning.realmdatalibrary.redao.RealmTrasCall;
import com.burning.realmdatalibrary.redao.RxReamlUtils;
import com.burning.reutils.ReHttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by burning on 2018/10/23.
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
public class EmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ReHttpUtils.initRetro("http://47.105.169.72:8989");
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("emq.realm").build();
        Realm.setDefaultConfiguration(config);
         startService(new Intent(this, Mqservices.class));
        LogUtils.init();
        RxReamlUtils rxReamlUtils = new RxReamlUtils();
        rxReamlUtils.updata(new RealmTrasCall() {
            @Override
            public void call(Realm realm) {
                RealmResults<UserPo> all = realm.where(UserPo.class).findAll();
                for (UserPo userPo : all) {
                    userPo.setUsername("========id===" + userPo.getId());
                }
                System.out.println("======RealmTrasCall========");
                List<MessagePo> list = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    MessagePo messagePo = new MessagePo();
                    if (i % 3 == 1) {
                        messagePo.setClientId(1);
                        messagePo.setOfclientID(2);
                    } else {
                        messagePo.setClientId(2);
                        messagePo.setOfclientID(1);
                    }
                    messagePo.setId(i + 100L);
                    messagePo.setCode(200);
                    messagePo.setContent("消息水水水水----" + i);
                    messagePo.setUuid("x");
                    list.add(messagePo);
                }
                String data = new Gson().toJson(list);
                realm.createAllFromJson(MessagePo.class, data);
               // realm.commitTransaction();
            }
        });
    }
}
