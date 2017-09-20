package com.chimu.myapp.hook.hook;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.util.Log;

import com.chimu.myapp.MyApplication;

import java.lang.reflect.Field;

/**
 * Created by Longwj on 2017/9/8.
 */

public class HookInstrumentation extends Instrumentation {

    private static final String TAG = "HookInstrumentation";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public HookInstrumentation(Instrumentation base) {
        mBase = base;
    }
    private void  getLoaderApk() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Application myApplication=MyApplication.myApplication;
        Field mLoadedApk=myApplication.getClass().getSuperclass().getDeclaredField("mLoadedApk");
        mLoadedApk.setAccessible(true);
        Object mLoadedApkObject=mLoadedApk.get(myApplication);
        Log.d("[app,hook]","获取的mLoadedApkObject="+mLoadedApkObject);
    }

    //重写创建Activity的方法
    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Log.d("[Hook]","哈哈，你被Hook了");
        try {
            getLoaderApk();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Log.d("[Hook]","className="+className+" intent="+intent);
        return super.newActivity(cl, className, intent);
    }
}
