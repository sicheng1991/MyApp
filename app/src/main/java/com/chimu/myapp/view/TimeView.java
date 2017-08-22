package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Longwj on 2017/8/15.
 */

public class TimeView extends FrameLayout implements View.OnClickListener{
    private int time;
    private Paint paint;
    private static Context context;
    private float centerX;
    private float centerY;
    RectF rectf;
    public TimeView(Context context) {
        super(context);
        init();
    }

    public TimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setOnClickListener(this);
        context = getContext();
        measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        centerY = (getTop() + getBottom()) / 2;
        centerX = (getRight() + getLeft()) / 2;
        Log.i("TimeView","onMeasure: "+centerX +":" + centerY);
        for(int i = 0;i < 12;i++){
            this.addView(new TimeItemView(context,i * 30,centerX,centerY));
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawOval(rectf,paint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    /**
     * dp 转成为 px
     */
    public static int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    @Override
    public void onClick(View v) {
        centerY = (getTop() + getBottom()) / 2;
        centerX = (getRight() + getLeft()) / 2;
        centerY = 100;
        centerX = 100;
        for(int i = 0;i < 12;i++){
            this.addView(new TimeItemView(context,i * 30,centerX,centerY));
        }
        Log.i("TimeView","onMeasure: "+centerX +":" + centerY);
    }
}
