package com.chimu.myapp;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chimu.mylib.LibApplication;
import com.chimu.mylib.util.PackageUtil;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

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
        initSophix();

    }

    private void initSophix() {

        // initialize最好放在attachBaseContext最前面
        SophixManager.getInstance().setContext(this)
                .setAppVersion(PackageUtil.getVersionCode(this) + "")
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        Log.i("SophixManager","mode:" + mode + "\ncode:" + code + "\ninfo:" + info + "\nhandlePatchVersion" + handlePatchVersion);
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
// queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
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
