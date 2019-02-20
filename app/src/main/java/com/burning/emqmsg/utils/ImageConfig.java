package com.burning.emqmsg.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by burning on 2019/2/19.
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
public class ImageConfig {
    public static String Image_path = "http://47.105.169.72/image/";

    private static String rootPath = "TakePhoto";
    private static String playPath = "";
    private final static String PHOTO_JPG_BASEPATH = "/" + rootPath + "/img/";
    private final static String PHOTO_COMPRESS_JPG_BASEPATH = "/" + rootPath + "/CompressImgs/";

    public static void setRootPath(String rootPath) {
        ImageConfig.rootPath = rootPath;
    }

    /**
     * @param fileName :System.currentTimeMillis() + ".jpg"//用时间戳
     * @return 获取保存原始文件的位置
     */

    public static String getJpgFileAbsolutePath(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            throw new NullPointerException("fileName isEmpty");
        }
        String photoPath = "";
        if (!fileName.endsWith(".jpg")) {
            fileName = fileName + ".jpg";
        }
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + PHOTO_JPG_BASEPATH;
        File file = new File(fileBasePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        photoPath = fileBasePath + fileName;
        return photoPath;
    }

    /**
     * 获取保存压缩图片文件的位置
     *
     * @return
     */
    public static String getCompressJpgFileAbsolutePath() {
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + PHOTO_COMPRESS_JPG_BASEPATH;
        File file = new File(fileBasePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        return fileBasePath;
    }
}
