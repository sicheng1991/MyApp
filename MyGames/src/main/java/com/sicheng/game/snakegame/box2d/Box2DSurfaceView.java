package com.sicheng.game.snakegame.box2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.sicheng.game.snakegame.R;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Created by yangzteL on 2018/10/30 0026.
 */

public class Box2DSurfaceView extends View{
    private final JboxImpl jboxImpl;
    private Paint paint;
    public Box2DSurfaceView(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
        jboxImpl = new JboxImpl(getResources().getDisplayMetrics().density);
        jboxImpl.createWorld();
        jboxImpl.creatBody();


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        jboxImpl.startWorld();
        float x = jboxImpl.getViewX() / 50;
        float y = jboxImpl.getViewY() / 50;
        Log.i("msggggg", "onDraw: " + x +":" + y);
        canvas.drawRect(x,y,x + 50,y + 50,paint);

        invalidate();
    }

}
