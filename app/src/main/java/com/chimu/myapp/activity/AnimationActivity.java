package com.chimu.myapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chimu.myapp.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Longwj on 2017/10/26.
 */

public class AnimationActivity extends AppCompatActivity{
    private LinearLayout ll_center;

    private TextView tv_a1;
    private TextView tv_a2;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    tv_a1.setVisibility(View.VISIBLE);
            }
            return false;
        }
    });
    private Timer timer = new Timer();
    private TimerTask tt = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_animation);
        initView();
    }

    private void initView() {
        ll_center = (LinearLayout) findViewById(R.id.ll_center);

        tv_a1 = new TextView(this);
        tv_a1.setText("如果");
        tv_a1.setTextSize(20);


        tv_a2 = new TextView(this);
        tv_a2.setText("所有的人");
        tv_a2.setTextSize(20);
        ll_center.addView(tv_a1);
        ll_center.addView(tv_a2);
        tv_a1.setVisibility(View.INVISIBLE);
        tv_a2.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAnimation();
    }

    private void initAnimation() {
        timer.schedule(tt,16 * 100);
    }
}
