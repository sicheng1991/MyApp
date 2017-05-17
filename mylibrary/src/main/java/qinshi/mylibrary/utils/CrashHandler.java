package qinshi.mylibrary.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/1/5.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return instance;
    }

    public void setCustomCrashHanler(Context context) {
        mContext = context;
        //崩溃时将catch住异常
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    //崩溃时触发
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        //使用Toast进行提示
        ToastUtils.showShort(mContext, "很抱歉，程序异常即将退出！");
        //延时退出
//        try {
//            thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //关闭APP
        System.exit(0);
    }

    //线程中展示Toast
//    private void showToast(final Context context, final String msg) {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
//                Looper.loop();
//            }
//        }).start();
//    }


}
