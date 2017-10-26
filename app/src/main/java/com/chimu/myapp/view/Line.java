package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 线
 * Created by Longwj on 2017/10/26.
 */

public class Line extends View {
    private final Interpolator interpolator;
    private final int toX;
    private final int toY;
    private final int fromY;
    private final int fromX;
    private int drawTime;
    private float index = 1;

    public Line(Context context, int drawTime,int fromX,int fromY,int toX,int toY, Interpolator interpolator) {
        super(context);
        this.drawTime = drawTime;
        this.toX = toX;
        this.toY = toY;
        this.fromY = fromY;
        this.fromX = fromX;
        this.interpolator = interpolator;
        init();
    }

    private Paint paint;

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        //设置画笔宽度
        paint.setStrokeWidth(3);
//消除锯齿
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        if(!isBegin){
            isBegin = true;
//            path = new Path();
//            path.moveTo(fromX,fromY);
//            float f = interpolator.getInterpolation((float)( 0.02 * index++));
//            path.lineTo(fromX +(toX - fromX) * f,fromY +(toY - fromY) * f);
            initDraw();
        }else{
            canvas.drawPath(path,paint);
        }
//        canvas.drawPath(path,paint);
    }

    private void initDraw() {
        final Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Line.this.post(new Runnable() {
                    @Override
                    public void run() {
                        float f = interpolator.getInterpolation((float)( 0.02 * index++));
                        path = new Path();
                        path.moveTo(fromX,fromY);
                        path.lineTo(fromX +(toX - fromX) * f,fromY +(toY - fromY) * f);
                        invalidate();
                        if(index >= 50){
                            timer.cancel();
                        }

                    }
                });
            }
        };
        timer.schedule(tt,drawTime / 50,drawTime / 50);
    }

    Path path = new Path();
    private boolean isBegin;

}
