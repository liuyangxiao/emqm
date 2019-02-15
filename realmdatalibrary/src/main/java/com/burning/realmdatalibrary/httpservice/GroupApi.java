package com.burning.realmdatalibrary.httpservice;

import com.burning.realmdatalibrary.po.GroupPo;

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
public interface GroupApi {
    /**
     * 创建 群组 或分组
     *
     * @param remark
     * @param content
     * @param uid
     * @param type
     * @param httpCallBack
     */
    void addGroupContent(String remark, String content, long uid, int type, HttpCallBack<String> httpCallBack);

    /**
     * 获取好友分组
     *
     * @param uid
     * @param httpCallBack
     */
    void getFrendGroup(long uid, HttpCallBack<GroupPo> httpCallBack);

    /**
     * 获取群分组
     *
     * @param uid
     * @param httpCallBack
     */
    void getGroups(long uid, HttpCallBack<GroupPo> httpCallBack);
}
