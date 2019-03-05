package com.burning.emqmsg;

import android.app.Application;
import android.content.Intent;
import android.os.Build;

import com.burning.emqmsg.service.Mqservices;
import com.burning.mybaselibrary.LogUtils;
import com.burning.reutils.ReHttpUtils;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
public class EmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ReHttpUtils.initRetro("https://47.105.169.72:8989");
        ReHttpUtils.instans().setHttps(true);
        Realm.init(this);
        LogUtils.init();
        RealmConfiguration config = new RealmConfiguration.Builder().name("emq.realm").build();
        Realm.setDefaultConfiguration(config);
        Intent intent = new Intent(this, Mqservices.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        Logger.d("==============EmApplication==============" + android.os.Process.myPid());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//            JobInfo.Builder builder = new JobInfo.Builder(333, new ComponentName(getPackageName(), NjobService.class.getName()));
//            builder.setMinimumLatency(3000); //执行的最小延迟时间
//            builder.setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);  //执行的最长延时时间
//            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
//            builder.setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR);//线性重试方案
//            //builder.setPeriodic()
//            JobInfo jobInfo = builder
//                    //  .setOverrideDeadline(10 * 1000)
//                    // .setPeriodic(3000)
//                    .setPersisted(true)
//                    .setRequiresCharging(true)
//                    .build();
//            jobScheduler.schedule(jobInfo);
//        }
    }

}
