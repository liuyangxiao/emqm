package com.burning.emqmsg

import com.burning.realmdatalibrary.BaSubCribe
import com.burning.realmdatalibrary.HttpApi
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import com.burning.realmdatalibrary.httpservice.requbean.LoginBean
import com.burning.realmdatalibrary.httpservice.requbean.ResDto
import com.burning.realmdatalibrary.po.UserPo
import com.burning.reutils.ReHttpUtils
import org.junit.Test
import rx.Observable

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        ReHttpUtils.initRetro("http://localhost:8989")
        var userapi = UserApimpl()
        var loginBean = LoginBean()
        loginBean.loginname = "string"
        loginBean.password = "string"
        ReHttpUtils.instans().httpRequestMain(object : BaSubCribe<ResDto<UserPo>>() {
            override fun getObservable(retrofit: HttpApi): Observable<ResDto<UserPo>>? {
                return retrofit.getLogin(loginBean)
            }

            override fun onCompleted() {
                print("==========onCompleted======")
            }


            override fun onError(e: Throwable) {
                print("==========onError======" + e)
            }

            override fun onNext(s: ResDto<UserPo>) {
                print("==========onNext======" + s)
                var xx: String = "xxx"
                xx.substring(100)
            }
        })

    }
}
