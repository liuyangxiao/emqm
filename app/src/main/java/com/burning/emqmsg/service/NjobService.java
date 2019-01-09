package com.burning.emqmsg.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;

import com.orhanobut.logger.Logger;


/**
 * Created by OneTheVE on 2018/12/28.
 */

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class NjobService extends JobService {

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, START_STICKY, startId);
    }
    @Override
    public boolean onStartJob(JobParameters params) {
        Logger.d("===========onStartJob========");
        startService(new Intent(this, RtService.class));
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Logger.d("===========onStopJob========");
        return false;
    }
}
