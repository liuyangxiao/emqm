package com.burning.realmdatalibrary.httpservice.impl;

import com.burning.realmdatalibrary.BaSubCribe;
import com.burning.realmdatalibrary.HttpApi;
import com.burning.realmdatalibrary.httpservice.FrendApi;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.realmdatalibrary.po.UserPo;
import com.burning.reutils.ReHttpUtils;

import java.util.List;
import java.util.Set;

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
public class FrendApimpl implements FrendApi {
    @Override
    public void getAddlist(final Long userid, final HttpCallBack<List<UserPo>> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<List<UserPo>>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<List<UserPo>> userPoResDto) {
                httpCallBack.oncode(200, userPoResDto.getMsg(), userPoResDto.getData());
            }

            @Override
            public Observable<ResDto<List<UserPo>>> getObservable(HttpApi retrofit) {
                return retrofit.getaddlist(userid);
            }
        });

    }

    @Override
    public void getFrendIds(final long userid, final HttpCallBack<Set<Long>> httpCallBack) {

    }

    @Override
    public void getFrendUsers(final long userid, final HttpCallBack<List<UserPo>> httpCallBack) {

    }

    @Override
    public void addFrend(final boolean isadd, final long userid, final long frendid, final String remarks, final long groupdId, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {

            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<String> resDto) {
                httpCallBack.oncode(200, resDto.getMsg(), resDto.getData());
            }

            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                if (isadd) {
                    return retrofit.addFrend(userid, frendid, remarks, groupdId);
                } else {
                    return retrofit.readdFrend(userid, frendid, remarks, groupdId);
                }
            }
        });
    }

    @Override
    public void getDeletfrend(final long userid, final long frendid, final HttpCallBack<String> httpCallBack) {

    }

    @Override
    public void updatafrendReName(final long userid, final long frendid, final String remarks, final HttpCallBack<String> httpCallBack) {

    }

    @Override
    public void updatafrendgroup(final long userid, final long frendid, final long groupid, final HttpCallBack<String> httpCallBack) {

    }
}
