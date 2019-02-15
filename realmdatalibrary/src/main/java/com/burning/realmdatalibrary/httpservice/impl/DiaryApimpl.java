package com.burning.realmdatalibrary.httpservice.impl;

import com.burning.realmdatalibrary.httpservice.DiaryApi;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryComment;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryMessage;

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
    public void getList(long uid, int page) {

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
