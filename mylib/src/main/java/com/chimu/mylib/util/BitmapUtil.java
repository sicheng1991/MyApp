package com.chimu.mylib.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
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

}
