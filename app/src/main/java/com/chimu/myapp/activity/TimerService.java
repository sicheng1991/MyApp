package com.chimu.myapp.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.chimu.myapp.R;

/**
 * Created by Longwj on 2017/8/31.
 */

public class TimerService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startForeground(startId,notification);


        return START_NOT_STICKY;
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
