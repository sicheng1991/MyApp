package com.sicheng.mydatabinding;

import android.app.Application;
import android.util.Log;

/**
 * Created by longwj on 2017/6/7.
 */

public class MyApplication extends Application {
    static public Application myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

    }
}
