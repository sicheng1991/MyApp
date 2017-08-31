package com.chimu.myapp.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chimu.myapp.R;

import java.util.List;

/**
 * Created by Longwj on 2017/8/25.
 */

public class TestLockActivity extends Activity implements View.OnClickListener {

    private TextView textView;

    private Animation alphaAnimation;

    private Context mContext;
    private boolean mIsExit = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mIsExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_lock_test);

        initBlinkAnimation();
        initView();
        setClickListener();
        setAnimation();
    }

    /**
     * 闪烁动画
     */
    private void initBlinkAnimation() {
        alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.REVERSE);
    }

    /**
     * 加载View
     */
    private void initView() {
        mContext = this;
    }

    /**
     * View设置监听
     */
    private void setClickListener() {
//        findViewById(R.id.relative_layout_click).setOnClickListener(this);
        final float[] motionEventX = {0};
        final float[] moveX = {0};
    }

    private void unLockWithPullUp(MotionEvent motionEvent, float[] motionEventX, float[] moveX) {
        int action = motionEvent.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            motionEventX[0] = motionEvent.getY();
        }
        if (action == MotionEvent.ACTION_MOVE) {
            moveX[0] = motionEvent.getY() - motionEventX[0];
        }
        if (action == MotionEvent.ACTION_UP) {
            if (moveX[0] < -50) {
                onClickToApplication();
            }
        }
    }

    /**
     * View设置动画
     */
    private void setAnimation() {
    }

    /**
     * 突破锁屏
     */
    public void onClickToApplication() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("");
        keyguardLock.disableKeyguard();
        finish();
        if (isBackGround(mContext)) {
            restartApplication();
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.relative_layout_click:
//                clickAgainToApplication();
//                break;
        }
    }

    private void restartApplication() {
        final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private static boolean isBackGround(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
            if (appProcessInfo.processName.equals(context.getPackageName())) {
                return appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND;
            }
        }
        return false;
    }

}
