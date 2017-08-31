package com.chimu.myapp.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chimu.myapp.R;

/**
 * Created by Longwj on 2017/8/31.
 */

public class TimerReceiver extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TimerReceiver","onReceive:"+"接受广播");
        this.context = context;
        noti();
    }

    private void noti() {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context,TestLockActivity.class), 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.a1)//设置小图标
                .setContentTitle("这是标题")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText("这是内容")
                .build();
        notificationManager.notify(0,notification);
    }
}
