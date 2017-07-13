package com.chimu.mylib;

import com.chimu.mylib.base.MApplication;

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

}
