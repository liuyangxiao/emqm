package com.burning.realmdatalibrary.po;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

/**
 * Created by burning on 2018/11/29.
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
@Data
public class UserPo extends RealmObject {
    @PrimaryKey
    Long id;
    /**
     * 登入账号
     */
    private String loginname;
    /**
     * 头像
     */
    private String icon;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 详情
     */
    private String userdesc;
    //
    /**
     * 用户名称
     */
    private String username;
    //
    /*    *//**
     * 用户密码
     *//*
    private String password;*/
    /**
     * 性别
     */
    private String gender;
    /**
     * 设定ID
     */
    private String setID;

}
