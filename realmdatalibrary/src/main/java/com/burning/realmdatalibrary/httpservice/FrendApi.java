package com.burning.realmdatalibrary.httpservice;

import com.burning.realmdatalibrary.po.UserPo;

import java.util.List;
import java.util.Set;

/**
 * Created by burning on 2019/2/14.
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
public interface FrendApi {
    /**
     * 获取好友请求列表
     */
    void getAddlist(Long userid, HttpCallBack<List<UserPo>> httpCallBack);

    /**
     * 获取所有好友ID
     */
    void getFrendIds(long userid, HttpCallBack<Set<Long>> httpCallBack);

    /**
     * 获取所有好友信息
     */
    void getFrendUsers(long userid, HttpCallBack<List<UserPo>> httpCallBack);

    /**
     * 添加好友
     */
    void addFrend(boolean isadd, long userid, long frendid, String remarks, long groupdId, HttpCallBack<String> httpCallBack);

    /**
     * 删除好友
     */
    void getDeletfrend(long userid, long frendid, HttpCallBack<String> httpCallBack);

    /**
     * 更改好友备注
     */
    void updatafrendReName(long userid, long frendid, String remarks, HttpCallBack<String> httpCallBack);

    /**
     * 更改好友分组
     */
    void updatafrendgroup(long userid, long frendid, long groupid, HttpCallBack<String> httpCallBack);
}
