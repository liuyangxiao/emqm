package com.burning.emqmsg.utils;

import android.content.Context;

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
    public static void sendMessage(Context context, DiarySendBean diarynoSendPo) {
        if (diarynoSendPo == null || diarynoSendPo.uid == 0 || diarynoSendPo.content == null)
            return;
        if (diarynoSendPo.icons != null && diarynoSendPo.icons.isEmpty()) {
            ImaComUtils.INSTANCE.uploudList(context, diarynoSendPo.icons, new ImaComUtils.Onuploads() {
                @Override
                public void onCallBack(@NotNull String string) {
                    //压缩上传

                }
            });
        } else {

        }
    }
}
