package com.chimu.myapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chimu.myapp.R;
import com.chimu.myapp.hook.hook.AMSHookUtil;
import com.chimu.myapp.hook.hook.HookInstrumentationUtil;
import com.chimu.mylib.util.BitmapUtil;

import com.example.annotation.Person;

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
        Bitmap bitmap =  BitmapFactory.decodeResource(this.getResources(),R.mipmap.c1);
        iv.setImageBitmap(BitmapUtil.getRoundBitmap(bitmap,10,true,false,false,true));



        HookInstrumentationUtil.hook();

//        双进程拉起
//        Intent i1 = new Intent(this, LocalService.class);
//        startService(i1);
//        Intent i2 = new Intent(this, RomoteService.class);
//        startService(i2);
    }



    public void OnClick(View v) {
        AMSHookUtil.hookStartActivity(this);
        Intent intent = new Intent(MainActivity.this, TestLockActivity.class);
        startActivity(intent);

//        startActivity(new Intent(this,TestLockActivity.class));
////        startAndBindService();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

