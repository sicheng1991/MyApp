package com.chimu.myapp.activity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chimu.myapp.R;
import com.chimu.myapp.common.BaseActivity;
import com.chimu.mylib.activity.MyCameraActivity;
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
        ImageView iv  = (ImageView) findViewById(R.id.img);
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

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, TimerReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 101, intent, 0);
        long nextTime = System.currentTimeMillis() + 10 * 1000;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Log.i("tagggg", "api大于19，开启下一个定时器" + format.format(new Date(nextTime)));
        } else
            alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
    }

    private void noti() {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,TestLockActivity.class), 0);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.a1)//设置小图标
                .setContentTitle("这是标题")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText("这是内容")
                .build();
        notificationManager.notify(0,notification);
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

