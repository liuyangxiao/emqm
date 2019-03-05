package com.burning.realmdatalibrary.httpservice.impl;

import com.burning.realmdatalibrary.BaSubCribe;
import com.burning.realmdatalibrary.HttpApi;
import com.burning.realmdatalibrary.UserInfo;
import com.burning.realmdatalibrary.httpservice.DiaryApi;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryComment;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryMessage;
import com.burning.realmdatalibrary.httpservice.requbean.ResDto;
import com.burning.realmdatalibrary.po.DiaryDescPo;
import com.burning.realmdatalibrary.po.DiaryPo;
import com.burning.realmdatalibrary.po.LoginUserPo;
import com.burning.realmdatalibrary.redao.RealmTrasCall;
import com.burning.realmdatalibrary.redao.RxReamlUtils;
import com.burning.reutils.ReHttpUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
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
            public void onNext(final ResDto<List<DiaryPo>> resDto) {
                if (!"200".equals(resDto.getCode()) || resDto.getData() == null || resDto.getData().size() == 0) {
                    httpCallBack.oncode(100, resDto.getMsg(), null);
                    return;
                }
                RxReamlUtils.updata(new RealmTrasCall() {
                    @Override
                    public void call(Realm realm) {
                        List<DiaryPo> data = resDto.getData();
                        //    String toJson = new Gson().toJson(data);
                        LoginUserPo loginUserPo = realm.where(LoginUserPo.class).equalTo("userid", UserInfo.userid).findFirst();
                        RealmList<DiaryPo> userPoDiaryPos = loginUserPo.getDiaryPos();
                        for (DiaryPo diaryPo : data) {
                            //  diaryPo.getId();
                            DiaryPo id = realm.where(DiaryPo.class).equalTo("id", diaryPo.getId()).findFirst();
                            if (id == null) {
                                //如原系统无此 ID 则添加到集合
                                userPoDiaryPos.add(diaryPo);
                            }
                        }
                        realm.insertOrUpdate(data);//更新或者插入 刷新的数据
                    }
                });

                httpCallBack.oncode(200, resDto.getMsg(), "OK");

            }
        });
    }

    @Override
    public void sendDiaryMessage(final DiaryMessage diaryMessage, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }

            @Override
            public void onNext(ResDto<String> resDto) {
                httpCallBack.oncode(200, resDto.getMsg(), resDto.getData());//发送成功
                getList(UserInfo.userid, 0, new HttpCallBack<String>() {
                    @Override
                    public void oncode(int code, String s, String data) {
                        //更新结果

                    }
                });
            }

            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                return retrofit.sendDiary(diaryMessage);
            }
        });
    }

    @Override
    public void descantMessage(final DiaryComment diaryComment, final HttpCallBack<String> httpCallBack) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<String>>() {
            @Override
            public void onError(Throwable e) {
                httpCallBack.oncode(100, e.getMessage(), null);
            }
            @Override
            public void onNext(ResDto<String> resDto) {
                httpCallBack.oncode(Integer.valueOf(resDto.getCode()), resDto.getMsg(), resDto.getMsg());//发送成功
                getDiaryid(diaryComment.getDiaryID());
            }

            @Override
            public Observable<ResDto<String>> getObservable(HttpApi retrofit) {
                return retrofit.sendDiarycomment(diaryComment);
            }
        });
    }

    @Override
    public void getDiaryid(final Long diaryid) {
        ReHttpUtils.instans().httpRequest(new BaSubCribe<ResDto<List<DiaryDescPo>>>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final ResDto<List<DiaryDescPo>> resDto) {
                if (Integer.valueOf(resDto.getCode()) == 200 && resDto.getData() != null && !resDto.getData().isEmpty()) {
                    RxReamlUtils.updata(new RealmTrasCall() {
                        @Override
                        public void call(Realm realm) {
                            DiaryPo mDiaryPo = realm.where(DiaryPo.class).equalTo("id", diaryid).findFirst();
                            if (mDiaryPo != null) {
                                RealmList<DiaryDescPo> diaryDescs = mDiaryPo.getDiaryDescs();
                                diaryDescs.deleteAllFromRealm();
                                diaryDescs.addAll(resDto.getData());
                            }
                        }
                    });
                }
            }

            @Override
            public Observable<ResDto<List<DiaryDescPo>>> getObservable(HttpApi retrofit) {
                return retrofit.getDiaryid(diaryid);
            }
        });
    }

    @Override
    public void delectMessage() {

    }
}
