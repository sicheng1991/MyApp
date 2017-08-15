package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.sql.Time;

/**
 * Created by Longwj on 2017/8/15.
 */

public class TimeItemView extends View{
    private int time;
    private Paint paint;
    private float rotation;
    private float centerX;
    private float centerY;
    private static Context context;
    RectF rectf;
    public TimeItemView(Context context,float rotation,float centerX,float centerY) {
        super(context);
        this.rotation = rotation;
        this.centerX = centerX;
        this.centerY = centerY;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        context = getContext();
        paint = new Paint();
        paint.setColor(Color.GRAY);
        rectf = new RectF(dip2px(0),dip2px(0),dip2px(6),dip2px(20));
        this.setPivotX(centerX);
        this.setPivotY(centerY);
        this.setRotation(rotation);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(rectf,paint);
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
}
