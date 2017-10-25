package com.chimu.mylib.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.chimu.mylib.LibApplication;
import com.chimu.mylib.bean.ExceptionBean;
import com.chimu.mylib.util.FileUtil;
import com.chimu.mylib.util.NetUtil;
import com.chimu.mylib.util.SystemUtil;
import com.chimu.mylib.util.ToastUtil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局异常捕获
 * Created by Longwj on 2017/10/25.
 */


public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static String CRASH_PATH = Environment.getExternalStorageDirectory().getPath()
            + "myapp/crash/";
    private Context mContext;

    static CrashHandler mCrashHandler;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            synchronized (CrashHandler.class) {
                if (mCrashHandler == null) {
                    mCrashHandler = new CrashHandler();
                }
            }
        }
        return mCrashHandler;
    }

    private CrashHandler() {
    }


    /**
     * 初始化
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.i("CrashHandler", "uncaughtException:" + e.getMessage());
        if (!handleException(e) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(t, e);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                Log.e("CrashHandler", "error : ", e1);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }


    }

    private boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        } else {
//            sendCrashToServer();//发送数据到服务器
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    ToastUtil.showShort(mContext, "程序崩溃,正在推出");
                    Looper.loop();
                }
            }.start();

            saveInfoInSD(e);
            saveInfo2DB(e);
            return true;
        }

    }

    /**
     * 数据库
     * @param e
     */
    private void saveInfo2DB(Throwable e) {
        try {
            ExceptionBean bean = new ExceptionBean();
            bean.setPage_name(e.getStackTrace()[0].getClassName());
//            bean.setException_name(e.getMessage());
            bean.setException_stack(e.getMessage());
            bean.setCrash_type("1");

            bean.setApp_version(SystemUtil.getAppVersionCode(LibApplication.application)+"");
            bean.setOs_versin(android.os.Build.VERSION.SDK_INT+ "");


            bean.setDevice_mac(SystemUtil.getIMEI(LibApplication.application));
            bean.setNetwork_type(NetUtil.getNetWorkState(LibApplication.application)+"");
            bean.setDevice_model(SystemUtil.getSystemModel());
            bean.setMemory_info("总内存：" + SystemUtil.getMemory_TOLAL()+ " 已使用："+ SystemUtil.getMemory_UNUSED(LibApplication.application));
            Log.e("CrashHandler", "bean : " + bean.toString());

        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    private void saveInfoInSD(Throwable e) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

            FileUtil.saveFile(formatter.format(new Date(System.currentTimeMillis())) + ".txt", e.getMessage());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
