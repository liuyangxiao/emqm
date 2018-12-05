package com.burning.realmdatalibrary.redao;

import com.burning.realmdatalibrary.po.UserPo;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * Created by burning on 2018/12/4.
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
public class UserDao {
    public void set() {
        Realm defaultInstance = Realm.getDefaultInstance();
        RealmAsyncTask realmAsyncTask = defaultInstance.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                String s = null;
                realm.createAllFromJson(UserPo.class, s);
                UserPo orUpdateObjectFromJson = realm.createOrUpdateObjectFromJson(UserPo.class, s);
                //  UserPo object = realm.createObject();
            }
        });
    }
}
