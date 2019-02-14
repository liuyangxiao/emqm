package com.burning.emqmsg.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * Created by burning on 2019/2/13.
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
public class MatGlideEngine implements ImageEngine {
    RequestOptions requestOptions = new RequestOptions()
            .centerCrop()
            .placeholder(new ColorDrawable(Color.BLACK))
            .error(new ColorDrawable(Color.BLUE))
            .fallback(new ColorDrawable(Color.RED));

    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions.override(resize))
                //   .asBitmap()  // some .jpeg files are actually gif
//                .placeholder(placeholder)
//                .override(resize, resize)
//                .centerCrop()
                .into(imageView);
    }

    public void loadAnimatedGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        loadGifThumbnail(context, resize, placeholder, imageView, uri);
    }

    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                 Uri uri) {
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions.override(resize))
                // .asBitmap()
//                .placeholder(placeholder)
//                .override(resize, resize)
//                .centerCrop()
                .into(imageView);
    }

    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .apply(requestOptions.override(resizeX, resizeY))
//                .override(resizeX, resizeY)
//                .priority(Priority.HIGH)
                .into(imageView);
    }

    public void loadAnimatedGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        loadGifImage(context, resizeX, resizeY, imageView, uri);
    }

    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(requestOptions.override(resizeX, resizeY))
//                .asGif()
//                .override(resizeX, resizeY)
//                .priority(Priority.HIGH)
                .into(imageView);
    }


    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
