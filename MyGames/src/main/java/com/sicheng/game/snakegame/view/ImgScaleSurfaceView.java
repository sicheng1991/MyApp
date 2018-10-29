package com.sicheng.game.snakegame.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.sicheng.game.snakegame.R;

/**
 * 图片放大缩小，双指
 * Created by yangzteL on 2018/10/26 0026.
 */

public class ImgScaleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;
    private float rate = 1;
    private float oldRate = 1;
    private float oldLineDistance;
    private float newLineDistance;
    private boolean isFirst = true;
    private int screenX;
    private int screenY;


    public ImgScaleSurfaceView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(50);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        init();
        myDraw();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.plane_bg);
        screenX = getWidth();
        screenY = getHeight();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("MySurfaceView", "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("MySurfaceView", "surfaceDestroyed");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                isFirst = true;
                oldRate = rate;
            } else {

                if (isFirst) {
                    oldLineDistance = (float) Math.sqrt(Math.pow(event.getX(1) - event.getX(), 2) + Math.pow(event.getY(1) - event.getY(), 2));
                    isFirst = false;
                } else {
                    newLineDistance = (float) Math.sqrt(Math.pow(event.getX(1) - event.getX(), 2) + Math.pow(event.getY(1) - event.getY(), 2));
                    rate = oldRate * newLineDistance / oldLineDistance;
                    myDraw();
                }
            }
        } catch (Exception e) {

        }

        return true;
    }


    private void myDraw() {
        try {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {

                canvas.save();
                //缩放画布
                canvas.scale(rate, rate, screenX / 2, screenY / 2);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, screenX / 2 - bitmap.getWidth() / 2, screenY / 2 - bitmap.getHeight() / 2, paint);
                canvas.restore();
            }

        } catch (Exception e) {

        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
