package com.burning.emqmsg.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.burning.emqmsg.R;
import com.orhanobut.logger.Logger;
import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.WechatLayoutManager;
import com.othershe.combinebitmap.listener.OnProgressListener;

/**
 * Created by burning on 2019/1/10.
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
public class UtilsImage {
    public static void combineBitmap(Context context, final ImageView view) {

        CombineBitmap.init(context)
                .setLayoutManager(new WechatLayoutManager()) // 必选， 设置图片的组合形式，支持WechatLayoutManager、DingLayoutManager
                .setSize(100) // 必选，组合后Bitmap的尺寸，单位dp
                //  .setGap() // 单个图片之间的距离，单位dp，默认0dp
                // .setGapColor() // 单个图片间距的颜色，默认白色
                //    .setPlaceholder() // 单个图片加载失败的默认显示图片
                //   .setUrls() // 要加载的图片url数组
                // .setBitmaps() // 要加载的图片bitmap数组
                .setResourceIds(new int[]{R.mipmap.a111, R.mipmap.ccatsfas, R.mipmap.a111, R.mipmap.ccatsfas}) // 要加载的图片资源id数组
                //   .setImageView(view) // 直接设置要显示图片的ImageView
                // 设置“子图片”的点击事件，需使用setImageView()，index和图片资源数组的索引对应
                /*  .setOnSubItemClickListener(new OnSubItemClickListener() {
                      @Override
                      public void onSubItemClick(int index) {

                      }
                  })*/
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onStart() {
                        Logger.d("==========" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete(Bitmap bitmap) {
                        //   view.setImageBitmap(bitmap);
                        Logger.d("==========" + Thread.currentThread().getName());
                    }
                })
                .build();

    }

    //new int[]{R.mipmap.a111, R.mipmap.ccatsfas, R.mipmap.a111, R.mipmap.ccatsfas}
    public static void combineBitmap(Context context, int[] ids, final Callback callback) {
        CombineBitmap.init(context)
                .setLayoutManager(new WechatLayoutManager()) // 必选， 设置图片的组合形式，支持WechatLayoutManager、DingLayoutManager
                .setSize(100) // 必选，组合后Bitmap的尺寸，单位dp
                //  .setGap() // 单个图片之间的距离，单位dp，默认0dp
                // .setGapColor() // 单个图片间距的颜色，默认白色
                //    .setPlaceholder() // 单个图片加载失败的默认显示图片
                //   .setUrls() // 要加载的图片url数组
                // .setBitmaps() // 要加载的图片bitmap数组
                .setResourceIds(ids) // 要加载的图片资源id数组
                //   .setImageView(view) // 直接设置要显示图片的ImageView
                // 设置“子图片”的点击事件，需使用setImageView()，index和图片资源数组的索引对应
                /*  .setOnSubItemClickListener(new OnSubItemClickListener() {
                      @Override
                      public void onSubItemClick(int index) {

                      }
                  })*/
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onStart() {
                        Logger.d("==========" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete(Bitmap bitmap) {
                        //   view.setImageBitmap(bitmap);
                        Logger.d("==========" + Thread.currentThread().getName());
                        callback.oncompleteBitmap(bitmap);
                    }
                })
                .build();

    }

    public interface Callback {
        void oncompleteBitmap(Bitmap bitmap);
    }
}
