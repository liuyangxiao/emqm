package com.burning.realmdatalibrary.httpservice.impl;

import com.burning.realmdatalibrary.BaSubCribe;
import com.burning.realmdatalibrary.HttpApi;
import com.burning.realmdatalibrary.UserInfo;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.UserApi;
import com.burning.realmdatalibrary.httpservice.requbean.LoginBean;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.realmdatalibrary.httpservice.requbean.UpdataUser;
import com.burning.realmdatalibrary.po.GroupPo;
import com.burning.realmdatalibrary.po.LoginUserPo;
import com.burning.realmdatalibrary.po.UserPo;
import com.burning.realmdatalibrary.redao.RealmTrasCall;
import com.burning.realmdatalibrary.redao.RxReamlUtils;
import com.burning.reutils.ReHttpUtils;
import com.google.gson.Gson;

import java.util.List;

import io.realm.Realm;
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
public class UserApimpl implements UserApi {

    @Override
    public void login(final LoginBean bean, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<UserPo>>() {
            @Override
            public Observable<ResDto<UserPo>> getObservable(HttpApi retrofit) {
                return retrofit.getLogin(bean);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("=====onError====");
                httpCallBack.oncode(100, "网络异常", e.getMessage());
            }

            @Override
            public void onNext(ResDto<UserPo> userPo) {
                System.out.println("====onNext=" + Thread.currentThread().getName());
                if (!"200".equals(userPo.getCode())) {
                    //错误
                    httpCallBack.oncode(0, userPo.getMsg(), "");
                    return;
                }
                Long id = userPo.getData().getId();
                UserInfo.userid = id;
                Realm defaultInstance = Realm.getDefaultInstance();
                LoginUserPo userid = defaultInstance.where(LoginUserPo.class).equalTo("userid", id).findFirst();
                defaultInstance.close();
                if (userid == null) {
                    initApp(new HttpCallBack<String>() {
                        @Override
                        public void oncode(int code, String s, String data) {
                            httpCallBack.oncode(code, s, data);
                        }
                    });
                } else {
                    httpCallBack.oncode(200, "已有数据", "");
                }


            }
        });
    }

    private void initApp(final HttpCallBack<String> httpCallBack) {
        System.out.println("===initApp main=" + Thread.currentThread().getName());
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<LoginUserPo>>() {
            @Override
            public Observable<ResDto<LoginUserPo>> getObservable(HttpApi retrofit) {
                return retrofit.initApp(UserInfo.userid);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("===initApp onError=" + Thread.currentThread().getName());
                httpCallBack.oncode(100, "网络异常initApp", e.getMessage());
            }

            @Override
            public void onNext(ResDto<LoginUserPo> resDto) {
                System.out.println("===httpRequestMain=onNext=" + Thread.currentThread().getName());
                if (!"200".equals(resDto.getCode())) {
                    //错误
                    httpCallBack.oncode(0, "service error code" + resDto.getMsg(), "");
                    return;
                }

                LoginUserPo data = resDto.getData();
                String toJson = new Gson().toJson(data);
                Realm defaultInstance = Realm.getDefaultInstance();
                defaultInstance.beginTransaction();
                //初始化 用户数据
                LoginUserPo orUpdateObjectFromJson = defaultInstance.createOrUpdateObjectFromJson(LoginUserPo.class, toJson);
                defaultInstance.commitTransaction();
                defaultInstance.close();
                if (orUpdateObjectFromJson == null) {
                    httpCallBack.oncode(100, "初始化异常", "createOrUpdateObjectFromJson null" + data);
                } else {
                    httpCallBack.oncode(200, "OK", "");
                }
            }
        });

    }

    @Override
    public void updataUser(final UpdataUser updataUser, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {
            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                return retrofit.updataUser(updataUser);
            }

            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<String> resDto) {
                httpCallBack.oncode(200, "OK", resDto.getData());
            }
        });
    }

    @Override
    public void getUserByUid() {

    }

    /**
     * 获取好友分组
     */
    @Override
    public void getUserFrendGroup() {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {
            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                return retrofit.getUserscontent(1111L);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResDto<String> resDto) {
                String data = resDto.getData();
                Realm defaultInstance = Realm.getDefaultInstance();
                defaultInstance.beginTransaction();
                //==保存好友分组
                defaultInstance.createOrUpdateAllFromJson(GroupPo.class, data);
                defaultInstance.commitTransaction();
                defaultInstance.close();
            }
        });

    }

    @Override
    public void getUserGroupDesc() {

    }

    @Override
    public void getUsersbyloginID() {

    }

    /**
     * 查找用户
     *
     * @param content
     * @param httpCallBack
     */
    @Override
    public void searchFrend(final String content, final HttpCallBack<List<UserPo>> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<List<UserPo>>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(final ResDto<List<UserPo>> userPoResDto) {
                httpCallBack.oncode(200, userPoResDto.getMsg(), userPoResDto.getData());
                if (userPoResDto != null && userPoResDto.getData() != null && userPoResDto.getData().size() != 0) {
                    RxReamlUtils.updata(new RealmTrasCall() {
                        @Override
                        public void call(Realm realm) {
                            //搜索后即同步数据库数据
                            realm.createOrUpdateAllFromJson(UserPo.class, new Gson().toJson(userPoResDto.getData()));
                        }
                    });
                }
            }

            @Override
            public Observable<ResDto<List<UserPo>>> getObservable(HttpApi retrofit) {
                return retrofit.searchfred(content);
            }
        });
    }

    @Override
    public void signup(final LoginBean bean, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, "注册异常:" + e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<String> resDto) {
                httpCallBack.oncode(200, resDto.getMsg(), resDto.getData());
            }

            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                return retrofit.creatUser(bean);
            }
        });
    }
}
