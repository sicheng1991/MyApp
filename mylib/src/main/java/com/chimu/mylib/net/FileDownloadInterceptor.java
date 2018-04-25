package com.chimu.mylib.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Longwj on 2018/4/24 0024.
 */

public class FileDownloadInterceptor implements Interceptor {
    private FileDownloadListener downloadListener;

    public FileDownloadInterceptor(FileDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new FileResponseBody(response.body(), downloadListener)).build();
    }
}
