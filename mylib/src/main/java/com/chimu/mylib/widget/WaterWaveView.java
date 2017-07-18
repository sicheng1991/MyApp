/*
 * Copyright (C) 2017 Qi Cai Technology Co., Ltd. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.chimu.mylib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class WaterWaveView extends ImageView {
    private final static int FPS = 1000 / 40;
    private final Paint mWavePaint = new Paint();
    //内圆距离顶部间距
    private int marginTop = 0;

    //内圆距离左边间距
    private int marginLeft = 0;

    private int marginBottom = 0;
    //波浪圆环间距
    private int WaveSpace = 0;

    //波浪圆环的最大半径
    private float WaveMaxR = 0;
    Float waveInitR;
    private String text = "拍照";
    private float circlex = 0;
    private float circley = 0;
    private String circleColor = "#ff42bde5";
    private float speed = 10f;
    private List<Float> circleList = new ArrayList<>();
    //波浪圆环的初始化半径
    private int initWaveSize;
    private Paint textPaint;
    private Rect textRect;
    private int baseLineY;

    public WaterWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaterWaveView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mWavePaint.setStrokeWidth(dip2px(1f));
        mWavePaint.setColor(Color.parseColor(circleColor));
        mWavePaint.setAntiAlias(true);
        mWavePaint.setStyle(Style.STROKE);
        speed = dip2px(8);

        marginTop = dip2px(110);
        marginLeft = dip2px(50);
        WaveSpace = dip2px(65);
    }

    public void initTextPaint() {
        textPaint = new Paint();
        textPaint.setColor(Color.parseColor(circleColor));
        textPaint.setTextSize(sp2px(20f));
        textPaint.setStyle(Style.FILL);
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        int textHeight = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        int textMarginTop = marginTop + (getWidth() - marginLeft * 2) + dip2px(25);
        textRect = new Rect(0, textMarginTop, getWidth(), textHeight + textMarginTop);//画一个矩形
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        baseLineY = (int) (textRect.centerY() - top / 2 - bottom / 2);//基线中间点的y轴计算公式
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        circlex = getWidth() / 2;
        circley = marginTop + (getWidth() - marginLeft * 2) / 2f;
        marginBottom = getHeight() - marginTop - (getWidth() - marginLeft * 2);
        WaveMaxR = Math.max(circley, getHeight() - circley);
        initWaveSize = (int) (Math.max(marginTop, marginBottom)) / WaveSpace;
        circleList.clear();
        waveInitR = (getWidth() - marginLeft * 2) / 2f + dip2px(8.5f);
        circleList.add(waveInitR);
        for (int a = 0; a < initWaveSize; a++) {
            circleList.add(waveInitR + (WaveSpace * (a + 1)));
        }
        initTextPaint();
        setImageBitmap(drawBackround());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        for (int a = 0; a < circleList.size(); a++) {
            Float r2 = circleList.get(a) + speed;
            if (r2 > WaveMaxR) {
                circleList.remove(a);
                a = a - 1;
            } else {
                circleList.set(a, r2);
            }
        }
//        if (initWaveSize > circleList.size()) {
//            circleList.add(0, waveInitR);
//        }
        if (circleList.size() == 0) {
            circleList.add(waveInitR);
            for (int a = 0; a < initWaveSize; a++) {
                circleList.add(waveInitR + (WaveSpace * (a + 1)));
            }
        }

        for (Float r : circleList) {
            int alpha = (int) ((WaveMaxR - r) / (WaveMaxR - waveInitR) * 204f);
            mWavePaint.setAlpha(204 - (204 - alpha));
            canvas.drawCircle(circlex, circley, r, mWavePaint);
        }

        canvas.drawText(text, textRect.centerX(), baseLineY, textPaint);
        postInvalidateDelayed(20);
    }

    private Bitmap drawBackround() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bmp = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        //背景
        canvas.drawColor(Color.parseColor("#e5ffffff"));
        {
            //裁剪一个透明圆
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            paint.setColor(Color.TRANSPARENT);
            canvas.drawCircle(circlex, circley, (getWidth() - marginLeft * 2) / 2f, paint);

        }

        paint.reset();
//        RectF circle = new RectF(dip2px(50f), marginTop, getWidth() - dip2px(50f), (getWidth() - dip2px(100)) + marginTop);
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sm), null, circle, paint);
        {
            //画内圆
            paint.setAntiAlias(false);
            paint.setStyle(Style.STROKE);
            paint.setStrokeWidth(dip2px(2f));
            paint.setColor(Color.parseColor(circleColor));
            canvas.drawCircle(circlex, circley, (getWidth() - marginLeft * 2) / 2f - dip2px(2f) / 2, paint);
        }
        {
            //画圆环
            paint.setStrokeWidth(dip2px(1f));
            canvas.drawCircle(circlex, circley, (getWidth() - marginLeft * 2) / 2f + dip2px(4.5f), paint);
            canvas.drawCircle(circlex, circley, (getWidth() - marginLeft * 2) / 2f + dip2px(8.5f), paint);
            paint.setColor(Color.parseColor("#ff9AD5EA"));
            paint.setStrokeWidth(dip2px(4f));
            canvas.drawCircle(circlex, circley, (getWidth() - marginLeft * 2) / 2f + dip2px(6.5f), paint);
        }
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmp;
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;//像素密度
        return (int) (dpValue * scale + 0.5f);
    }

    public int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public RectF getXY() {
        return new RectF(dip2px(50f), marginTop, getWidth() - dip2px(50f), (getWidth() - dip2px(100)) + marginTop);
    }

    public void setTxt(String text) {
        this.text = text;
        postInvalidate();
    }
}