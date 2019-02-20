package com.burning.realmdatalibrary.httpservice.impl;

import com.burning.realmdatalibrary.BaSubCribe;
import com.burning.realmdatalibrary.HttpApi;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.reutils.ReHttpUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by burning on 2019/2/13.
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
public class HttpUpload {
    /**
     * 上传图片
     *
     * @param file
     * @param httpCallBack
     */
    public static void upload(final File file, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<String> loginUserPoResDto) {
                httpCallBack.oncode(200, "OK", loginUserPoResDto.getData());
            }

            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                return retrofit.uoloadFile(body);
            }
        });

    }

    /**
     * 上传图片
     *
     * @param file
     * @param httpCallBack
     */
    public static void uploadOnthis(final File file, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequestMain(new BaSubCribe<ResDto<String>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<String> loginUserPoResDto) {
                httpCallBack.oncode(200, "OK", loginUserPoResDto.getData());
            }

            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                return retrofit.uoloadFile(body);
            }
        });

    }
}
