package com.example.chimu.myapp;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by longwj on 2017/6/7.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
    }

    private void initARouter() {
        if (EXTERNAL_RELEASE) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
    public static final boolean EXTERNAL_RELEASE = BuildConfig.isDebug;
}
