package com.burning.realmdatalibrary.httpservice;

import com.burning.realmdatalibrary.BaSubCribe;
import com.burning.realmdatalibrary.HttpApi;
import com.burning.reutils.ReHttpUtils;

import rx.Observable;

/**
 * Created by burning on 2018/12/3.
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
public class UserApi {
    public void ccc(final HttpCallBack httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<String>() {
            @Override
            public Observable<String> getObservable(HttpApi retrofit) {
                return retrofit.getUsers(1111);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                httpCallBack.onerror();
            }

            @Override
            public void onNext(String s) {
                httpCallBack.seccuss(s);
            }
        });
    }
}
