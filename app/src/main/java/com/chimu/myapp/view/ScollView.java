package com.chimu.myapp.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;


/**
 *
 * view滑动demo
 * Created by Longwj on 2017/8/8.
 */

public class ScollView extends View{
    private Paint paint;

    private int lastX;
    private int lastY;
    private Context context;


    public ScollView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ScollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        setClickable(true);
        Scroller scroller = new Scroller(context);
        ViewGroup group;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectf=  new RectF(0,0,50,50);
        canvas.drawOval(rectf,paint);
    }

    //实现移动:onTouchEvent：注意:setClickable(true);
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //手指按下的坐标
        int y = (int) event.getY();
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //滑动距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;

//                1.通过layout重新设置view位置
//                layout(getLeft()+offsetX, getTop()+offsetY,
//                        getRight()+offsetX , getBottom()+offsetY);

//                2.通过offsetLeftAndRight()与offsetTopAndBottom()
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

//                3.改变布局参数
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetX;
//                layoutParams.topMargin = getTop() + offsetY;
//                setLayoutParams(layoutParams);

//                4.使用动画
//                ObjectAnimator.ofFloat(this,"translationX",0,300).setDuration(1000).start();
//                5.scollTo(移动到)与scollBy（移动多少）
                ((View)getParent()).scrollBy(-offsetX,-offsetY);


                break;
        }
        return super.onTouchEvent(event);
    }
}
