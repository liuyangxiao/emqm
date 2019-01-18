package com.burning.realmdatalibrary;

import com.burning.realmdatalibrary.httpservice.requbean.LoginBean;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.realmdatalibrary.po.LoginUserPo;
import com.burning.realmdatalibrary.po.UserPo;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by burning on 2018/12/3.
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
public interface HttpApi {

    /**
     * 创建用户
     *
     * @param bean
     * @return
     */
    @POST("/user/creat")
    Observable<ResDto<String>> creatUser(@Body LoginBean bean);

    /**
     * 登入
     *
     * @param bean
     * @return
     */
    @POST("/user/login")
    Observable<ResDto<UserPo>> getLogin(@Body LoginBean bean);

    /**
     * 初始化数据
     *
     * @param uid
     * @return
     */
    @POST("user/initapp")
    Observable<ResDto<LoginUserPo>> initApp(@Query("uid") Long uid);

    /**
     * 查找用户
     *
     * @param uid
     * @return
     */
    @GET("user/finduser")
    Observable<ResDto<List<UserPo>>> searchfred(@Query("user") String uid);

    /**
     * 根据ID 获取好友分组
     *
     * @param uid
     * @return
     */
    @POST("content/selectbyuid")
    Observable<ResDto<String>> getUserscontent(@Query("uid") Long uid);

    @POST("frend/getusers")
    Observable<String> getUsersByuid(@Query("uid") Long uid);


}
