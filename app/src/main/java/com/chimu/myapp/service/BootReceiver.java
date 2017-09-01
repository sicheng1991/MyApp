package com.chimu.myapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chimu.myapp.activity.MainActivity;

/**
 * 需要开机启动权限
 *
 * Created by Longwj on 2017/9/1.
 */

public class BootReceiver extends BroadcastReceiver {

    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
//            Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
//            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(mainActivityIntent);
            Intent intent1 = new Intent(context, TimerService.class);
            context.startService(intent1);
            Log.i("loggggg","onReceive:"+"接受到广播");
        }
    }
}
