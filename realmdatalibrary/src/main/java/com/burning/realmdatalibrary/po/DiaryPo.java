package com.burning.realmdatalibrary.po;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import lombok.Data;

/**
 * Created by burning on 2018/12/4.
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
@Data
@RealmClass
public class DiaryPo implements RealmModel {
    @PrimaryKey
    Long id;
    /**
     * 图片
     */
    String icons;
    /**
     * 创建人
     */
    Long uid;
    /**
     * 内容
     */
    String contens;
    /**
     * 其下所有的评论
     */
    RealmList<DiaryDescPo> diaryDescPos;
}
