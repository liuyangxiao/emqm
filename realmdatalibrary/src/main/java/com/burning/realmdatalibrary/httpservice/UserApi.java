package com.burning.realmdatalibrary.httpservice;

import com.burning.realmdatalibrary.httpservice.requbean.LoginBean;

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
public interface UserApi {
    /**
     * 登入
     */
    void login(LoginBean bean, HttpCallBack<String> httpCallBack);

    /**
     * 修改用户信息
     */
    void updataUser();

    /**
     * 根据用户ID获取用户信息
     */
    void getUserByUid();

    /**
     * 获取用户分组及信息
     */
    void getUserFrendGroup();

    /**
     * 获取用户相关群信息
     */
    void getUserGroupDesc();

    /**
     * 获取所有好友信息
     */
    void getUsersbyloginID();
}
