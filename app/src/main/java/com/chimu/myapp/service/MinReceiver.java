package com.chimu.myapp.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chimu.myapp.activity.MainActivity;

/**
 *
 *
 * 每分钟广播
 *
 * 只能动态注册
 *     IntentFilter filter = new IntentFilter();
 filter.addAction(Intent.ACTION_TIME_TICK);
 registerReceiver(new MinReceiver(), filter);
 * Created by Longwj on 2017/9/1.
 */

public class MinReceiver extends BroadcastReceiver {

    static final String ACTION = "android.intent.action.TIME_TICK";
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
