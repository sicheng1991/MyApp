package com.chimu.mylib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Longwj on 2017/7/14.
 */

public class PackageUtil {
    /**
     * app版本号
     * @param context
     * @return
     */
  public static int getVersionCode(Context context){
      PackageManager pm = context.getPackageManager();
      PackageInfo pi = null;
      try {
          pi = pm.getPackageInfo(context.getPackageName(),
                  PackageManager.GET_CONFIGURATIONS);
      } catch (PackageManager.NameNotFoundException e) {
          e.printStackTrace();
      }
      return pi.versionCode;
  }

    /**
     * app版本名
     * @param context
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi.versionName;
    }

    /**
     * app信息
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context){
        PackageManager pm = context.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi;
    }
}
