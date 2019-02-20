package com.burning.emqmsg

import com.burning.realmdatalibrary.httpservice.impl.HttpUpload
import com.burning.reutils.ReHttpUtils
import org.junit.Test
import java.io.File

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        ReHttpUtils.initRetro("https://localhost:8989")
        //  ReHttpUtils.initRetro("http://47.105.169.72:8989")
//        HttpUpload.upload(File("C:\\Users\\burning\\Documents\\Tencent Files\\349652514\\FileRecv\\MobileFile\\Screenshot_2017-08-27-22-35-39.png")) { i: Int, s: String, s1: String ->
//
//        }
        //  DownloadUtils
        ReHttpUtils.instans().setHttps(true)
        HttpUpload.upload(File("C:\\Users\\burning\\Desktop\\ShadowsocksR-4.7.0\\user.rule")) { i: Int, s: String, s1: String ->
            print("===data=====$s1")
        }
    }
}
