package com.burning.emqmsg;

import android.app.Application;

import com.burning.realmdatalibrary.po.UserPo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

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
        // ReHttpUtils.initRetro("http://47.105.169.72:8989");
        Realm.init(this);
       /* RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);*/
        Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.beginTransaction();
        Long id = 1000L;
        List<UserPo> data = new ArrayList();
        for (int i = 0; i < 10; i++) {
            UserPo userPo = new UserPo();
            userPo.setId(id + i);
            userPo.setUsername("ccdddxxxd" + i + "====");
            data.add(userPo);
        }
        String s = new Gson().toJson(data);
        defaultInstance.createOrUpdateAllFromJson(UserPo.class, s);
        defaultInstance.commitTransaction();
    }
}
