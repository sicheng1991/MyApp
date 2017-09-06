package com.chimu.myapp.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chimu.myapp.R;
import com.chimu.myapp.aidl.LocalService;
import com.chimu.myapp.aidl.RomoteService;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Person(name = "龙文江", age = 35)
public class MainActivity extends BaseActivity {
    private Animation animation;
    private ImageView   iv;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity", "onCreate:" + activityState);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
       iv = (ImageView) findViewById(R.id.img);
        button = (Button) findViewById(R.id.btn_commit);

        //双进程拉起
//        Intent i1 = new Intent(this, LocalService.class);
//        startService(i1);
//        Intent i2 = new Intent(this, RomoteService.class);
//        startService(i2);


        animation = new TranslateAnimation(0,300,0,300);
        animation.setDuration(3000);               //设置动画持续时间
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(1000);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        button.startAnimation(animation);
        iv.startAnimation(animation);
//
    }

    public void OnClick(View v) {
//        animation.cancel();
        iv.clearAnimation();
        button.clearAnimation();


    }

}

