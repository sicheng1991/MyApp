package com.example.myutils.uitls;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Longwj on 2017/7/13.
 */

public class BitmapUtil {


    /**
     *    Activity变bitmap
     * @param activity
     * @return
     */
    private static Bitmap takeScreenShot(Activity activity){
        View view =activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        System.out.println(statusBarHeight);
        int width =activity.getWindowManager().getDefaultDisplay().getWidth();
        int height =activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap bitmap2 = Bitmap.createBitmap(bitmap,0,statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bitmap2;
    }




    /**
     * 图片任意圆角
     * @param bitmap
     * @param roundPx  圆角大小
     * @return
     */
    public static Bitmap getRoundBitmap(Bitmap bitmap, int roundPx,boolean isTLRound,boolean isTRRound,boolean isBLRound,boolean isBRRound) {
        try {
            final int width = bitmap.getWidth();
            final int height = bitmap.getHeight();

            Bitmap bitmap1 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap1);//画布传入空bitmap，这个bitmap就是canvas画的图
            canvas.drawARGB(Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT);

            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);

            //画出4个圆角
            final RectF rectF = new RectF(0, 0, width, height);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            //把不需要圆角的补上
            patchRound(canvas,paint,roundPx,height,width,isTLRound,isTRRound,isBLRound,isBRRound);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//多图层只绘制重叠部分
            final Rect src = new Rect(0, 0, width, height);
            final Rect dst = src;
            canvas.drawBitmap(bitmap, src, dst, paint);

            return bitmap1;
        } catch (Exception exp) {
            return bitmap;
        }
    }

    private static void patchRound(Canvas canvas,Paint paint,int roundPx,int height,int width,boolean isTLRound,boolean isTRRound,boolean isBLRound,boolean isBRRound){
        if(!isTLRound){
            drawRect(canvas,paint,roundPx,0,0);
        }
        if (!isTRRound){
            drawRect(canvas,paint,roundPx,width - roundPx,0);
        }
        if(!isBLRound){
            drawRect(canvas,paint,roundPx,0,height - roundPx);
        }
        if (!isBRRound){
            drawRect(canvas,paint,roundPx,width - roundPx,height - roundPx);
        }
    }
    private static void drawRect(Canvas canvas,Paint paint,int roundPx,int x,int y){
        final Rect rect = new Rect(x, y, x + roundPx, y + roundPx);
        canvas.drawRect(rect, paint);
    }
}
