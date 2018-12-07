package com.burning.realmdatalibrary;

import com.burning.realmdatalibrary.httpservice.requbean.LoginBean;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.realmdatalibrary.po.UserPo;
import com.burning.reutils.ReHttpUtils;

import org.junit.Test;

import rx.Observable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ReHttpUtils.initRetro("http://localhost:8080");
        final LoginBean loginBean = new LoginBean();
        loginBean.setLoginname("string");
        loginBean.setPassword("string");
        ReHttpUtils.instans().httpRequestMain(new BaSubCribe<ResDto<UserPo>>() {
            @Override
            public Observable<ResDto<UserPo>> getObservable(HttpApi retrofit) {
                return retrofit.getLogin(loginBean);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("=======onError=");
            }

            @Override
            public void onNext(ResDto<UserPo> s) {
                System.out.println("=======onNext="+s);
            }
        });

    }
}