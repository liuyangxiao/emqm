package com.burning.emqmsg.utils

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import com.burning.realmdatalibrary.httpservice.impl.HttpUpload
import com.google.gson.Gson
import com.zyao89.view.zloading.ZLoadingDialog
import com.zyao89.view.zloading.Z_TYPE
import top.zibin.luban.Luban
import top.zibin.luban.OnCompressListener
import java.io.File

/**
 * Created by burning on 2019/2/19.
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
object ImaComUtils {
    fun luban(context: Context, file: File) {

        var dialog = ZLoadingDialog(context)
        Luban.with(context)
                .load(file)
                .ignoreBy(100)
                .setTargetDir(ImageConfig.getCompressJpgFileAbsolutePath())
                .filter { path -> !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif")) }
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {
                        dialog?.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
                                ?.setLoadingColor(Color.BLUE)//颜色
                                ?.setHintText("图片压缩...")
                                ?.show()
                    }

                    override fun onSuccess(file: File) {
                        dialog.dismiss()
                    }

                    override fun onError(e: Throwable) {
                        dialog.dismiss()
                    }
                }).launch()
    }

    fun <T : Comparable<T>> luban(context: Context, list: List<T>) {
        var dialog = ZLoadingDialog(context)

        Luban.with(context)
                .load(list)
                .ignoreBy(100)
                .setTargetDir(ImageConfig.getCompressJpgFileAbsolutePath())
                .filter { path -> !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif")) }
                .setCompressListener(object : OnCompressListener {
                    override fun onStart() {
                        dialog?.setLoadingBuilder(Z_TYPE.SNAKE_CIRCLE)//设置类型
                                ?.setLoadingColor(Color.BLUE)//颜色
                                ?.setHintText("图片压缩...")
                                ?.show()
                    }

                    override fun onSuccess(file: File) {
                        dialog.dismiss()
                    }

                    override fun onError(e: Throwable) {
                        dialog.dismiss()
                    }
                }).launch()
    }


    fun uploudList(context: Context, file: List<File>, onuoload: Onuploads) {
        Thread {
            var icons = ArrayList<String>()
            for (file1 in Luban.with(context).load(file).get()) {//压缩
                HttpUpload.uploadOnthis(file1) { i: Int, s: String, data: String ->
                    //上传--
                    if (i == 200 && data != null) {
                        icons.add(data)//添加
                    }
                }

            }
            onuoload.onCallBack(Gson().toJson(icons))
        }.start()
    }

    interface Onuploads {
        fun onCallBack(string: String)
    }
}
