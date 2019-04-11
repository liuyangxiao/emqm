package com.burning.emqmsg

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        ReHttpUtils.initRetro("https://localhost:8989")
//        //  ReHttpUtils.initRetro("http://47.105.169.72:8989")
////        HttpUpload.upload(File("C:\\Users\\burning\\Documents\\Tencent Files\\349652514\\FileRecv\\MobileFile\\Screenshot_2017-08-27-22-35-39.png")) { i: Int, s: String, s1: String ->
////
////        }
//        //  DownloadUtils
//        ReHttpUtils.instans().setHttps(true)
//        HttpUpload.upload(File("C:\\Users\\burning\\Desktop\\ShadowsocksR-4.7.0\\user.rule")) { i: Int, s: String, s1: String ->
//            print("===data=====$s1")
//        }
        val data = MutableLiveData<String>()
        val observer = Observer<String> { s -> println("======" + s + "====" + Thread.currentThread()) }
        data.observeForever(observer)
        //  data.observeForever();
        data.setValue("打你吗")
        data.postValue("大傻逼")

    }
}
