package com.burning.emqmsg;

import android.app.Application;

import com.burning.mybaselibrary.LogUtils;
import com.burning.realmdatalibrary.redao.RxReamlUtils;
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
        // startService(new Intent(this, Mqservices.class));
        LogUtils.init();
        RxReamlUtils rxReamlUtils = new RxReamlUtils();
        rxReamlUtils.t();
    }
}
