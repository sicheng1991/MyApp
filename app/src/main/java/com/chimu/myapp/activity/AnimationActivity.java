package com.chimu.myapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chimu.myapp.R;
import com.chimu.myapp.view.Circle;
import com.chimu.myapp.view.Line;
import com.chimu.mylib.util.MetricUtil;

import java.util.Timer;
import java.util.TimerTask;

import static com.chimu.mylib.util.MetricUtil.*;

/**
 * Created by Longwj on 2017/10/26.
 */

public class AnimationActivity extends AppCompatActivity{
    private LinearLayout ll_center;
    private RelativeLayout rl_perent;
    private int width;
    private int heigh;

    private TextView tv_a1;
    private TextView tv_a2;
    private Circle[] cs = new Circle[4];

    @IdRes
    int TGA_001 = 10001;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){//时间轴
                case 5:
                    tv_a1.setVisibility(View.VISIBLE);
                    break;
                case 10:
                    tv_a2.setVisibility(View.VISIBLE);
                    break;
                case 15:
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(left,top,0,0);
                    rl_perent.addView(cs[0],lp);
                    break;
                case 17:
                    RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp1.setMargins(left + 400,top,0,0);
                    rl_perent.addView(cs[1],lp1);
                    break;
                case 19:
                    RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp2.setMargins(left + 400,top  + 400,0,0);
                    rl_perent.addView(cs[2],lp2);
                    break;
                case 21:
                    RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp3.setMargins(left,top + 400,0,0);
                    rl_perent.addView(cs[3],lp3);
                    break;

                case 25:
                    cs[0].animate().translationX(400).setDuration(2000).setInterpolator(new AccelerateDecelerateInterpolator());
                    cs[1].animate().translationY(400).setDuration(2000).setInterpolator(new AccelerateDecelerateInterpolator());
                    cs[2].animate().translationX(-400).setDuration(2000).setInterpolator(new AccelerateDecelerateInterpolator());
                    cs[3].animate().translationY(-400).setDuration(2000).setInterpolator(new AccelerateDecelerateInterpolator());
                    rl_perent.addView(new Line(AnimationActivity.this,2000,left + size,top + size,left + 400 + size ,top + size,new AccelerateDecelerateInterpolator()));
                    rl_perent.addView(new Line(AnimationActivity.this,2000,left + size + 400,top + size,left + 400 + size ,top + size + 400,new AccelerateDecelerateInterpolator()));
                    rl_perent.addView(new Line(AnimationActivity.this,2000,left + size + 400,top + size + 400,left + size ,top + size + 400,new AccelerateDecelerateInterpolator()));
                    rl_perent.addView(new Line(AnimationActivity.this,2000,left + size,top + size + 400,left + size ,top + size,new AccelerateDecelerateInterpolator()));
                    break;
            }
            return false;
        }
    });
    private  int fps = 0;
    private Timer timer = new Timer();
    private TimerTask tt = new TimerTask() {
        @Override
        public void run() {
            handler.sendEmptyMessage(fps++);
        }
    };
    private int top;
    private int left;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_animation);
        width = MetricUtil.getWindowWith(this);
        heigh = MetricUtil.getWindowHeight(this);
        initView();
    }

    private void initView() {
        ll_center = (LinearLayout) findViewById(R.id.ll_center);
        rl_perent = (RelativeLayout) findViewById(R.id.rl_parent);

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

        cs[0] = new Circle(this,size);
        cs[1] = new Circle(this,size);
        cs[2] = new Circle(this,size);
        cs[3] = new Circle(this,size);

        top = (heigh - 400) / 2;
        left = (width - 400) / 2;
    }
    int size = 12;

    @Override
    protected void onResume() {
        super.onResume();
        initAnimation();
    }

    private void initAnimation() {
        if(!isBegin){
            timer.schedule(tt,16 * 20,16 * 20);
            isBegin = true;
        }
    }
    private boolean isBegin;
}
