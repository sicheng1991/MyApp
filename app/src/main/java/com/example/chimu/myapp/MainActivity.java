package com.example.chimu.myapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Bundle;
import android.util.Log;

import com.example.chimu.myapp.download.DownLoadUtils;
import com.example.chimu.myapp.download.DownloadApk;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.kernal.plateid.MemoryCameraActivity;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private AlarmManager am;
    private Intent intent;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Log.i("aaaaaaaa", "开启任务：" + System.currentTimeMillis());

            startService(intent);
        }
    };
    private SharedPreferences sharedPreferences;
   private PendingIntent pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //1.注册下载广播接收器
//        DownloadApk.registerBroadcast(this);
//        //2.删除已存在的Apk
//        DownloadApk.removeFile(this);
//
//        //网络情况;
//        ConnectionClassManager manager = ConnectionClassManager.getInstance();
//        ConnectionQuality quality = manager.getCurrentBandwidthQuality();
//        Double bits = manager.getDownloadKBitsPerSecond();
//        Log.i("msgggg","当前网速："+ bits);
        Intent intent = new Intent(this,MemoryCameraActivity.class);
        startActivity(intent);
    }

    private void OnClick(){
        //3.如果手机已经启动下载程序，执行downloadApk。否则跳转到设置界面
        if (DownLoadUtils.getInstance(getApplicationContext()).canDownload()) {
            DownloadApk.downloadApk(getApplicationContext(), "http://www.huiqu.co/public/download/apk/huiqu.apk", "Hobbees更新", "Hobbees");
        } else {
            DownLoadUtils.getInstance(getApplicationContext()).skipToDownloadManager();
        }
    }
    @Override
    protected void onDestroy() {
        am.cancel(pi);//取消了
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}


