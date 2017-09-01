package com.chimu.myapp.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chimu.myapp.R;
import com.chimu.myapp.common.BaseActivity;
import com.chimu.myapp.service.MinReceiver;
import com.chimu.myapp.service.TimerService;
import com.chimu.mylib.util.AnnotationUtil;
import com.chimu.mylib.util.FileUtil;
import com.chimu.mylib.util.SPUtils;
import com.example.annotation.Person;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Person(name = "龙文江", age = 35)
public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate:" + activityState);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        ImageView iv = (ImageView) findViewById(R.id.img);
        Button button = (Button) findViewById(R.id.btn_commit);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10 * 1000);
//                    noti();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(Intent.ACTION_TIME_TICK);
//        registerReceiver(new MinReceiver(), filter);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        Intent intent = new Intent(this, TimerReceiver.class);
//        PendingIntent pi = PendingIntent.getBroadcast(this, 101, intent, 0);
        Intent intent = new Intent(this, TimerService.class);
        PendingIntent pi = PendingIntent.getService(this, 101, intent, 0);
        long nextTime = System.currentTimeMillis() + 10 * 1000;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Log.i("tagggg", "api大于19，开启闹钟" + format.format(new Date(nextTime)));
        } else
            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
    }

    //Arouter页面跳转
    public void OnClick(View v) {
//        aRouter();

        String s1 = AnnotationUtil.getPerson(this);
        SPUtils spUtils = new SPUtils(this, "tessssst");
        spUtils.put("text", "测试呢");
        String s = "";
        File f = new File(Environment.getDataDirectory().getPath() + "/data/" + this.getPackageName() + "/");
        FileUtil.copyPath(f, Environment.getExternalStorageDirectory());
        Toast.makeText(this, s1, Toast.LENGTH_SHORT).show();
    }

}

