package com.chimu.myapp;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chimu.mylib.LibApplication;
import com.example.chimu.myapp.BuildConfig;

/**
 * Created by longwj on 2017/6/7.
 */

public class MyApplication extends Application {
    LibApplication libApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
        initLib();

    }

    private void initLib() {
        libApplication  = LibApplication.getInstance();
        libApplication.onCreate(this);
    }

    private void initARouter() {
        if (EXTERNAL_RELEASE) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
    public static final boolean EXTERNAL_RELEASE = BuildConfig.isDebug;

    @Override
    public void onTerminate() {
        super.onTerminate();
        libApplication.onTerminate();
    }
}
