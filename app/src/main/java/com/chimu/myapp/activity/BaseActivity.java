package com.chimu.myapp.activity;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 可感知生命周期的activity
 * Created by Longwj on 2017/8/8.
 */

public class BaseActivity extends Activity{
    public static final int ON_CAREATE = 1;
    public static final int ON_START = 2;
    public static final int ON_RESUME = 3;
    public static final int ON_PAUSE = 4;
    public static final int ON_STOP = 5;
    public static final int ON_DESTORY = 6;
    public static final int ON_RESTORT = 7;

    public int activityState = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityState = ON_CAREATE;
    }

    @Override
    protected void onStart() {
        super.onStart();
        activityState = ON_START;
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ON_RESUME;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ON_PAUSE;
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityState = ON_STOP;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        activityState = ON_RESTORT;
    }
}
