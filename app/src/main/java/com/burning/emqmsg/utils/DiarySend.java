package com.burning.emqmsg.utils;

import android.content.Context;

import com.burning.realmdatalibrary.UserInfo;
import com.burning.realmdatalibrary.httpservice.DiaryApi;
import com.burning.realmdatalibrary.httpservice.HttpCallBack;
import com.burning.realmdatalibrary.httpservice.impl.DiaryApimpl;
import com.burning.realmdatalibrary.httpservice.requbean.DiaryMessage;
import com.orhanobut.logger.Logger;

import org.jetbrains.annotations.NotNull;

/**
 * Created by burning on 2019/2/20.
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
public class DiarySend {
    public static void sendMessage(Context context, final DiarySendBean diarynoSendPo) {
        if (diarynoSendPo == null || diarynoSendPo.uid == 0 || diarynoSendPo.content == null)
            return;
        if (diarynoSendPo.icons != null) {
            ImaComUtils.INSTANCE.uploudList(context, diarynoSendPo.icons, new ImaComUtils.Onuploads() {
                @Override
                public void onCallBack(@NotNull String string) {
                    //压缩上传完毕
                    DiaryApi diaryApi = new DiaryApimpl();
                    DiaryMessage diaryMessage = new DiaryMessage();
                    diaryMessage.setUid(UserInfo.userid);
                    diaryMessage.setIcons(string);
                    diaryMessage.setContent(diarynoSendPo.getContent());
                    diaryApi.sendDiaryMessage(diaryMessage, new HttpCallBack<String>() {
                        @Override
                        public void oncode(int code, String s, String data) {
                            Logger.d("======sendDiaryMessage==oncode=====" + code);
                            if (code != 200) {

                            }
                        }
                    });
                }
            });
        }
    }
}
