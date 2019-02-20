package com.burning.realmdatalibrary.httpservice.impl;

import com.burning.realmdatalibrary.BaSubCribe;
import com.burning.realmdatalibrary.HttpApi;
import com.burning.realmdatalibrary.httpservice.DiaryApi;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryComment;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryMessage;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.realmdatalibrary.po.DiaryPo;
import com.burning.reutils.ReHttpUtils;
import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by burning on 2019/2/15.
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
public class DiaryApimpl implements DiaryApi {
    @Override
    public void getList(final long uid, final int page, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<List<DiaryPo>>>() {
            @Override
            public Observable<ResDto<List<DiaryPo>>> getObservable(HttpApi retrofit) {
                return retrofit.diarygetlist(uid, page);
            }

            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<List<DiaryPo>> resDto) {
                if (!"200".equals(resDto.getCode()) || resDto.getData() == null || resDto.getData().size() == 0) {
                    httpCallBack.oncode(100, resDto.getMsg(), null);
                    return;
                }
                List<DiaryPo> data = resDto.getData();
                String toJson = new Gson().toJson(data);
                Realm defaultInstance = Realm.getDefaultInstance();
                defaultInstance.beginTransaction();
                //保存
                defaultInstance.createOrUpdateAllFromJson(DiaryPo.class, toJson);
                defaultInstance.commitTransaction();
                httpCallBack.oncode(200, resDto.getMsg(), "OK");
            }
        });
    }

    @Override
    public void sendDiaryMessage(DiaryMessage diaryMessage, HttpCallBack<String> httpCallBack) {

    }

    @Override
    public void descantMessage(DiaryComment diaryComment, HttpCallBack<String> httpCallBack) {

    }

    @Override
    public void delectMessage() {

    }
}
