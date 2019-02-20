package com.burning.realmdatalibrary.httpservice;

import com.burning.realmdatalibrary.httpservice.requbean.DiaryComment;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryMessage;

/**
 * Created by burning on 2018/12/4.
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
public interface DiaryApi {
    /**
     * 获取定量的日记信息
     * 包含-内消息
     */
    void getList(long uid, int page,HttpCallBack<String> httpCallBack);

    /**
     * 发布一条动态消息
     */
    void sendDiaryMessage(DiaryMessage diaryMessage, HttpCallBack<String> httpCallBack);

    /**
     * 评论一条动态消息
     */
    void descantMessage(DiaryComment diaryComment, HttpCallBack<String> httpCallBack);

    /**
     * 删除一条发布消息
     */
    void delectMessage();


}
