package com.example.chimu.myapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**自动更新服务
 * Created by longwj on 2017/3/27.
 */

public class UpdateService1 extends Service{
    @Override
    public void onCreate() {


        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
