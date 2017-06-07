package com.example.chimu.myapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.kernal.plateid.MemoryCameraActivity;
import java.util.TimerTask;
import bean.PlateRecogBean;

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
//        //文通识别调用
//        Intent intent = new Intent(this,MemoryCameraActivity.class);
//        startActivityForResult(intent,100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == MemoryCameraActivity.RecogReasultCode){
            PlateRecogBean bean = data.getParcelableExtra(MemoryCameraActivity.RecogReasult);
            Log.i("msggggg","扫描到车牌：" +bean.toString());

        }
    }

    @Override
    protected void onDestroy() {
        am.cancel(pi);//取消了
        stopService(intent);
        super.onDestroy();
    }

}


