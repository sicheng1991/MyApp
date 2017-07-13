package com.chimu.mylib.base;

import android.app.Application;

/**
 * Created by Longwj on 2017/7/13.
 */

public abstract class MApplication {
    public static Application application;
    public  void onCreate(Application application){
        this.application = application;
    }


    public  void onTerminate(){}


}
