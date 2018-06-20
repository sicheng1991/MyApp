package com.example.myutils.uitls;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.chimu.mylib.LibApplication;

/**
 * Created by Longwj on 2017/7/13.
 */

public class MyDownloadManager {

    public static void download(String url) {
        DownloadManager downloadManager = (DownloadManager) LibApplication.application.getSystemService(LibApplication.application.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        Long id = downloadManager.enqueue(request);  //制指定下载任务的id
        //downloadManager.remove(REFERENCE_1, REFERENCE_2, REFERENCE_3);    //取消下载
        request.setTitle("网络嗅探器5.5");      //标题
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);   //wifi下载
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);//通知
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();
        //设置文件存放路劲
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "网络嗅探器5.5.zip");
        Toast.makeText(LibApplication.application, "开始下载", Toast.LENGTH_SHORT).show();
    }
}
