package com.chimu.mylib;

import android.app.Application;

import com.chimu.mylib.base.MApplication;
import com.chimu.mylib.common.CrashHandler;

/**
 * Created by Longwj on 2017/7/13.
 */

public class LibApplication extends MApplication{
    public static LibApplication libApplication;

    public static LibApplication getInstance() {
        if (libApplication == null) {
            synchronized (LibApplication.class) {
                if (libApplication == null) {
                    libApplication = new LibApplication();
                }
            }
        }
        return libApplication;
    }

    @Override
    public void onCreate(Application application) {
        super.onCreate(application);
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(application.getApplicationContext());
    }
}
