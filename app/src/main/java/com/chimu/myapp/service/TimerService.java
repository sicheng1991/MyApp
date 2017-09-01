package com.chimu.myapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chimu.myapp.R;
import com.chimu.myapp.activity.TestLockActivity;

/**
 * Created by Longwj on 2017/8/31.
 */

public class TimerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,TestLockActivity.class), 0);
//        NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
//
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.mipmap.a1)//设置小图标
//                .setContentTitle("前台服务")
//                .setAutoCancel(true)
//                .setContentIntent(pendingIntent)
//                .setContentText("前台服务")
//                .build();
//        startForeground(111,notification);//设置为前台服务
        Log.i("loggggg","onCreate:");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("loggggg","onStartCommand:开启服务");

        noti();
        return START_NOT_STICKY;
//        return START_REDELIVER_INTENT;
    }
    Notification notification;
    private void noti() {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,TestLockActivity.class), 0);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);

        notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.a1)//设置小图标
                .setContentTitle("这是标题")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText("这是内容")
                .build();
        notificationManager.notify(0,notification);
    }
}
