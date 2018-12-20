package com.burning.realmdatalibrary.redao;

import com.burning.realmdatalibrary.po.UserPo;

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
    public void t() {
        UserPo userPo = new UserPo();
        userPo.setUsername("xxxx");
        userPo.setAge(1);
        userPo.setIcon("ccc");
        Observable.just(userPo)
                /*    Observable.create(new SyncOnSubscribe<String, UserPo>() {
                        @Override
                        protected String generateState() {
                            int l = new Random().nextInt(100);
                            System.out.println(Thread.currentThread().getName() + "=====generateState======" + l);
                            return String.valueOf(l);
                        }

                        @Override
                        protected String next(String state, Observer<? super UserPo> observer) {
                            System.out.println("state==" + state + "======" + Thread.currentThread().getName() + "====next===" + state);
                            //observer.onNext(new UserPo());
                            return null;
                        }
                    })*/
                //.subscribeOn(Schedulers.io())//执行线程
                .observeOn(AndroidSchedulers.mainThread())//回调线程
                .subscribe(new Subscriber<UserPo>() {
                    @Override
                    public void onCompleted() {
                        System.out.println(Thread.currentThread().getName() + "====onCompleted===");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(Thread.currentThread().getName() + "====onError===");
                    }

                    @Override
                    public void onNext(UserPo userPo) {
                        System.out.println(Thread.currentThread().getName() + "====onNext===" + userPo);
                    }
                })
        ;
    }
}
