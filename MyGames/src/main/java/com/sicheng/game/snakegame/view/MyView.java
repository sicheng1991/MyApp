package com.sicheng.game.snakegame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.View;

import com.sicheng.game.snakegame.util.MetricUtil;

/**
 * Created by yangzteL on 2018/10/25 0025.
 */

public class MyView extends View {
    private final Paint paint;
    private int x;
    private int y = 50;

    public MyView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(MetricUtil.dip2px(getContext(), 20));
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("MyView", 0, 50, paint);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            x -= 2;
        }
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            x += 2;
        }
        invalidate();
        return super.onKeyDown(keyCode, event);
    }
}
