package com.sicheng.snakegame.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.sicheng.snakegame.util.MetricUtil;

/**
 * 方格
 *
 * Created by Longwj on 2017/8/28.
 */

public class Block extends View {
    Context context;
    float X;
    float Y;
    Paint paint;
    public Block(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.STROKE);
    }
    public Block(Context context,float X,float Y) {
        super(context);
        setPosition(X,Y);
        this.context = context;
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }
    public Block(Context context,float X,float Y,int color) {
        super(context);
        setPosition(X,Y);
        this.context = context;
        paint = new Paint();
        paint.setColor(color);
//        paint.setStyle(Paint.Style.STROKE);
    }
    public void setPosition(float X,float Y){
        this.X = X;
        this.Y = Y;
    }
    public float getX(){
        return  X;
    }
    public float getY(){
        return  Y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(X,Y, X + 15,Y + 15);
        canvas.drawRect(rectF,paint);
    }
}
