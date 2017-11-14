package com.chimu.mylib.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
   static String BASE_URL = "http://223.85.163.38:88/stopcar/v1/";//外网测试ip   泸州
    //   static  String BASE_URL = "http://app.qc-wbo.com/v1/";  //阿里
    private static final int DEFAULT_TIMEOUT = 20;
    private static OkHttpClient okHttpClient;
    public static String baseUrl = BASE_URL;
    private static Context mContext;
    private static RetrofitClient sNewInstance;

    private static Retrofit retrofit;
    private Cache cache = null;
    private File httpCacheDirectory;


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl);
    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder()
//                    .addNetworkInterceptor(
//                            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient(
                mContext);
    }

    public static RetrofitClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    public static RetrofitClient getInstance(Context context, String url) {
        if (context != null) {
            mContext = context;
        }

        return new RetrofitClient(context, url);
    }

    public static RetrofitClient getInstance(Context context, String url,
                                             Map<String, String> headers,
                                             Interceptor interceptor) {
        if (context != null) {
            mContext = context;
        }
        return new RetrofitClient(context, url, headers,interceptor);
    }
    public static RetrofitClient getInstance(Context context,
                                             Interceptor interceptor) {
        if (context != null) {
            mContext = context;
        }
        return new RetrofitClient(context, baseUrl, null,interceptor);
    }
    private RetrofitClient() {

    }

    private RetrofitClient(Context context) {

        this(context, baseUrl, null,null);
    }

    private RetrofitClient(Context context, String url) {

        this(context, url, null,null);
    }

    private RetrofitClient(Context context, String url, Map<String, String> headers,
                           Interceptor interceptor) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "tamic_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
            }
        } catch (Exception e) {
            Log.e("OKHttp", "Could not create http cache", e);
        }
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new NovateCookieManger(context))
                .cache(cache)
//                TODO 设置公共的 header
                .addInterceptor(interceptor)
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new LoggingInterceptor())

//                .addInterceptor(new CaheInterceptor(context))
//                .addNetworkInterceptor(new CaheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();

    }


    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }
}
