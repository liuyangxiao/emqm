package com.burning.emqmsg.glidehelp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 * Created by burning on 2018/7/9.
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
public class JmModelLoader implements ModelLoader<String, InputStream> {

    private Context context;

    public JmModelLoader(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull String glideUrl, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(glideUrl), new JmDataFetcher(context, glideUrl));
    }

    @Override
    public boolean handles(@NonNull String glideUrl) {
        //检查 url的格式形式 以检测是否是 可执行内容
        return glideUrl.contains("@");
    }
}
