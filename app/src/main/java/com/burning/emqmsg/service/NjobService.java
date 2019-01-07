package com.burning.emqmsg.service;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

/**
 * Created by OneTheVE on 2018/12/28.
 */

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class NjobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
