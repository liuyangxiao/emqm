package com.burning.emqmsg.glidehelp;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.burning.emqmsg.R;

/**
 * Created by burning on 2018/11/2.
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
public class MyTransform {
    private static int roundingRadius;

    public static RequestOptions getRequestOptions(int roundingRadius) {
        RoundedCorners roundedCorners = new RoundedCorners(roundingRadius);
        return new RequestOptions()
                .transforms(new CenterCrop(), roundedCorners)
                .placeholder(R.mipmap.a111)
                .error(R.mipmap.ic_launcher);
    }

    public static RequestOptions getCircleCrop() {
        return RequestOptions.bitmapTransform(new CircleCrop())
                .placeholder(R.mipmap.a111)
                .error(R.mipmap.ic_launcher);
    }


}
