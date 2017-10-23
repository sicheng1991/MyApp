package com.chimu.mylib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Longwj on 2017/10/23.
 */

public class RuleView extends View {
    private Paint paint;

    public RuleView(Context context) {
        super(context);
        paint = new Paint();
        paint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0, 200, 20 * 10000, 350);
        paint.setColor(Color.parseColor("#FFFFF0"));
        this.setClickable(true);
        canvas.drawRect(rectF, paint);
        drawScale(canvas);


    }

    private void drawScale(Canvas canvas) {
        paint.setColor(Color.parseColor("#CDCDC1"));

        for (int i = 0; i < 500; i++) {
            int index = i + ind;

            if (i % 10 == 0) {
                RectF rect1 = new RectF(index * 20, 200, index * 20 + 2, 200 + 80);
                canvas.drawRect(rect1, paint);
                canvas.drawText((i / 10 - ind) + "", index * 20, 200 + 120, paint);
            } else {
                RectF rect2 = new RectF(index * 20, 200, index * 20 + 2, 200 + 40);
                canvas.drawRect(rect2, paint);
            }

        }
    }

    //    float oldx = 0;
//    float newx = 0;
    int ind = 0;

    //    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//
//        if(MotionEvent.ACTION_DOWN == event.getAction()){
//            oldx = event.getRawX();
////            y = event.getRawY();
//        }else if(MotionEvent.ACTION_UP == event.getAction()){
//            newx = event.getRawX();
//            this.animate().translationX(newx - oldx);
////            ind = (int) ((newx - oldx) / 20);
//            Log.i("RuleView","onTouchEvent:"+"newx:" + newx + " oldx："+ oldx);
//            oldx = newx;
//        }
//
//
//        return super.onTouchEvent(event);
//    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(10000, 1000);
    }
}
