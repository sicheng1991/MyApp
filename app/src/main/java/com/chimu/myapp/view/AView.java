package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yangzteL on 2018/12/28 0028.
 */

public class AView extends View {

    Paint paint = new Paint();
    Path path = new Path();

    public AView(Context context) {
        super(context);
        init(context);
    }

    public AView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }
    private void init(Context context){
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRoundRect(0,0,Cons.BOX_WIDTH,Cons.BOX_HEIGHT,
                Cons.BOX_RADIUS, Cons.BOX_RADIUS,paint);

        path.moveTo(0,0);
        path.rCubicTo(Cons.BOX_WIDTH / 2 ,Cons.BOX_HEIGHT / 2 ,
                Cons.BOX_WIDTH / 2 ,Cons.BOX_HEIGHT / 2 ,
                Cons.BOX_WIDTH,0);//三阶贝塞尔
        paint.setStyle(Paint.Style.STROKE);//绘制线条
        paint.setColor(Color.BLUE);
        canvas.drawPath(path,paint);
    }
}



