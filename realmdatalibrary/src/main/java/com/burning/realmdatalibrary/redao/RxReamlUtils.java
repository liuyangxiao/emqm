package com.burning.realmdatalibrary.redao;

import io.realm.Realm;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by burning on 2018/12/19.
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
public class RxReamlUtils {
    public void t(final RealmTrasCall realmTrasCall) {
        Observable<Realm> observable = Observable.unsafeCreate(new Observable.OnSubscribe<Realm>() {
            @Override
            public void call(final Subscriber<? super Realm> subscriber) {
                System.out.println(Thread.currentThread().getName() + "====call===");
                Realm defaultInstance = Realm.getDefaultInstance();
                defaultInstance.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realmTrasCall.call(realm);
                        subscriber.onNext(realm);
                        subscriber.onCompleted();
                    }
                });
            }
        });
        RealmSubcriber realmSubcriber = new RealmSubcriber();
        observable
                // .subscribeOn(Schedulers.io())//执行线程
                .observeOn(AndroidSchedulers.mainThread())//回调线程
                .subscribe(realmSubcriber);
    }
}
