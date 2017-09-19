package com.chimu.mylib.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.chimu.mylib.LibApplication;
import com.chimu.mylib.R;
import com.chimu.mylib.util.MetricUtil;


/**
 *
 * 通过WindowManager直接挂载view
 * Created by Longwj on 2017/9/11.
 */

public class VeinPopupActivity implements View.OnClickListener {
    private static WindowManager.LayoutParams mLayoutParams;
    private static WindowManager mWindowManager;
    private static Context mContext;
    private View mView;
    private TextView tv_cancel;

    public VeinPopupActivity(Context context) {
        mContext = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mLayoutParams = new WindowManager.LayoutParams();
        initView();
        setListener();
    }
    public void dismissPop(){
        mView.setVisibility(View.GONE);
    }

    private void setListener() {
        tv_cancel.setOnClickListener(this);
    }

    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.popup_vein_pay, null);
        createWindow(mView);
        tv_cancel = (TextView) mView.findViewById(R.id.tv_cancel);
    }


    private void createWindow(View view) {
//        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;   //设置window type
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mLayoutParams.format = PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        mLayoutParams.gravity = Gravity.CENTER;   //调整悬浮窗口在屏幕中间，便于调整坐标
//        mLayoutParams.x = 0;
//        mLayoutParams.y = 0;
        mLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mLayoutParams.width = (int) (MetricUtil.getWindowWith(LibApplication.application) * 0.8);
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mWindowManager.addView(view, mLayoutParams);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.tv_cancel){
            mView.setVisibility(View.GONE);
        }
    }
}
