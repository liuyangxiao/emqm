package com.burning.emqmsg.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.burning.mybaselibrary.SystemBarTintManager;

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

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(R.color.text_color_blue);
//        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // enable status bar tint
        tintManager.setStatusBarTintEnabled(true);
        // enable navigation bar tint
        tintManager.setNavigationBarTintEnabled(true);
       // setRootViewFitsSystemWindows(this,true);
        setContentView(getActivityLayout());
        if (realm == null)
            realm = Realm.getDefaultInstance();
        System.out.println("====================" + getClass().getName());
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

    public void startMyActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);

    }

    public void startMyActivity(Intent intent) {
        startActivity(intent);
    }

}
