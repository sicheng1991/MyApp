package com.chimu.myapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 圆
 * Created by Longwj on 2017/10/26.
 */

public class Circle extends View {
    private int size;
    public Circle(Context context,int size) {
        super(context);
        this.size = size;
        init();
    }

    private Paint paint;

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(size,size,size,paint);
    }


}
