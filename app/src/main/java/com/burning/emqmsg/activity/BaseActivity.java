package com.burning.emqmsg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.burning.mybaselibrary.DpPxTransformUtil;
import com.gyf.barlibrary.ImmersionBar;

import io.realm.Realm;

/**
 * Created by burning on 2018/10/23.
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
public abstract class BaseActivity extends AppCompatActivity {
    public Realm realm;
    public static int actionBarHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (actionBarHeight == 0) {
            //   actionBarHeight = getActionBarHeight(this);
            actionBarHeight = DpPxTransformUtil.dip2px(this, 25);
        }
        setContentView(getActivityLayout());
        if (realm == null)
            realm = Realm.getDefaultInstance();
        System.out.println("====================" + getClass().getName());
        // fullScreen();
        ImmersionBar.with(this)
                //  .fitsSystemWindows(true)
                //   .titleBarMarginTop(new View(this))
                .init();
        init();
    }

    @Override
    protected void onResume() {
        if (realm == null)
            realm = Realm.getDefaultInstance();
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        System.out.println("====onCreate================" + getClass().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("====onStop================" + getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        if (realm != null)
            realm.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("====onPause================" + getClass().getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存销毁之前的数据

        System.out.println("====onSaveInstanceState================" + getClass().getName());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("====onRestoreInstanceState================" + getClass().getName());
    }


    abstract int getActivityLayout();

    abstract void init();

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void startMyActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);

    }

    public void startMyActivity(Intent intent) {
        startActivity(intent);
    }


}
