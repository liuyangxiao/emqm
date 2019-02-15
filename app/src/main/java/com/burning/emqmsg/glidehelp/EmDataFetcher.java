package com.burning.emqmsg.glidehelp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.burning.emqmsg.view.UtilsImage;

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
public class EmDataFetcher implements DataFetcher<Bitmap> {
    String jid;
    Context context;

    public EmDataFetcher(Context context, String jid) {
        this.context = context;
        this.jid = jid;
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataCallback<? super Bitmap> callback) {
        callback.onDataReady(getImage());
    }
    private Bitmap getImage() {
//        Bitmap myBitmap = Glide.with(context.getApplicationContext())
//                .load(yourUrl)
//                .asBitmap() //必须
//                .centerCrop()
////                .into(500, 500)
//                .get();

        final Bitmap[] getbitmap = new Bitmap[1];
        UtilsImage.combineBitmap(context,jid , new UtilsImage.Callback() {
            @Override
            public void oncompleteBitmap(Bitmap bitmap) {
                getbitmap[0] = bitmap;
            }
        });
        return getbitmap[0];
    }

    @Override
    public void cleanup() {
        //资源关闭
    }

    @Override
    public void cancel() {
        //取消
    }

    @NonNull
    @Override
    public Class<Bitmap> getDataClass() {
        return Bitmap.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.DATA_DISK_CACHE;
    }
}
