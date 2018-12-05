package com.burning.emqmsg

import com.burning.realmdatalibrary.httpservice.HttpCallBack
import com.burning.realmdatalibrary.httpservice.impl.UserApimpl
import com.burning.reutils.ReHttpUtils
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        ReHttpUtils.initRetro("http://localhost:8080")
        var userapi = UserApimpl()
        userapi.ccc(object : HttpCallBack {
            override fun onerror() {
                print("onerror" )
            }

            override fun seccuss(s: String?) {
                print("===s=" + s)
            }

        })

    }
}
