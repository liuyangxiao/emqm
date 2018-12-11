package com.burning.emqmsg;

import android.app.Application;

import com.burning.reutils.ReHttpUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
       /* Realm defaultInstance = Realm.getDefaultInstance();
        defaultInstance.beginTransaction();
        LoginUserPo loginUserPo = new LoginUserPo();
        RealmList<GroupPo> realmList = new RealmList<>();
        List<GroupPo> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GroupPo groupPo = new GroupPo();
            groupPo.setId(1000L + i);
            groupPo.setRemarks("xxx" + i);
            RealmList<UserPo> userPos = new RealmList<>();
            for (int k = 0; k < 5; k++) {
                UserPo userPo = new UserPo();
                userPo.setId(k*i + 10000L);
                userPo.setUsername("KKKKKKL" + k*i);
                userPos.add(userPo);
            }
            groupPo.setList(userPos);
            data.add(groupPo);
        }
        realmList.addAll(data);
        loginUserPo.setUserid(3333l);
        loginUserPo.setGroupPos(realmList);
        String s = new Gson().toJson(loginUserPo);
        defaultInstance.createOrUpdateObjectFromJson(LoginUserPo.class, s);
       *//* Long id = 1000L;
        List<UserPo> data = new ArrayList();
        for (int i = 0; i < 10; i++) {
            UserPo userPo = new UserPo();
            userPo.setId(id + i);
            userPo.setUsername("ccdddxxxd" + i + "====");
            data.add(userPo);
        }
        String s = new Gson().toJson(data);
        defaultInstance.createOrUpdateAllFromJson(UserPo.class, s);*//*
        defaultInstance.commitTransaction();*/
    }
}
