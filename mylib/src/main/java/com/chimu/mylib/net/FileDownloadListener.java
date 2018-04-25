package com.chimu.mylib.net;

/**
 * 文件下载
 * Created by Longwj on 2018/4/24 0024.
 */

public interface FileDownloadListener {
    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload();

    void onFail(String errorInfo);
}
