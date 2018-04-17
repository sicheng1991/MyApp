package com.chimu.myapp.common;

/**
 * 调用本地方法，包名要和本地方法的包名一致，可以在 选择方法 alt + enter 在本地方法中生成方法，来替换原来的文件
 *
 * Created by Longwj on 2018/4/17 0017.
 */

public class ImageProc {
    private static ImageProc imageProc;

    public ImageProc(){}

    public static ImageProc instance(){
        if (imageProc == null){
            imageProc = new ImageProc();
        }
        return imageProc;
    }

    static {
        System.loadLibrary("camera-lib");
    }

    public int init(){
        return  CaptureCamera();
    }
    public static native int CaptureCamera();
}
