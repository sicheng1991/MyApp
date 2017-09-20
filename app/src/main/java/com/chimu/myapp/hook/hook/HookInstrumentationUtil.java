package com.chimu.myapp.hook.hook;

import android.app.Instrumentation;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Longwj on 2017/9/20.
 */

public class HookInstrumentationUtil {
    public static void hook() {
        // 先获取到当前的ActivityThread对象
        try {
            Class<?> activityThread =Class.forName("android.app.ActivityThread");
            Method currentActivityThread=activityThread.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            //获取主线程对象
            Object activityThreadObject=currentActivityThread.invoke(null);//静态方法可以传null对象

            //获取Instrumentation字段
            Field mInstrumentation=activityThread.getDeclaredField("mInstrumentation");
            mInstrumentation.setAccessible(true);
            Instrumentation instrumentation= (Instrumentation) mInstrumentation.get(activityThreadObject);
            HookInstrumentation customInstrumentation = new HookInstrumentation(instrumentation);
            //替换掉原来的,就是把系统的instrumentation替换为自己的Instrumentation对象
            mInstrumentation.set(activityThreadObject,customInstrumentation);
            Log.d("[Hook]","Hook Instrumentation成功");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
