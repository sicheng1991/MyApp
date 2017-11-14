package com.chimu.mylib.net;

import android.content.Context;
import android.text.TextUtils;


import com.chimu.mylib.util.SPUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ParametersInterceptor implements Interceptor {
    private Map<String, String> parameters;
    SPUtils spUtils;

    public ParametersInterceptor(Context context) {
        spUtils = new SPUtils(context, "LKS");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl.Builder builder = originalHttpUrl.newBuilder();
        parameters = new HashMap<>();

        parameters.put("token", spUtils.getString("token"));
        parameters.put("pu_nd", spUtils.getString("pu_nd"));
        if (parameters.size() > 0) {
            Set<String> keys = parameters.keySet();
            for (String headerKey : keys) {
                if(!TextUtils.isEmpty(parameters.get(headerKey))){
                    builder.addQueryParameter(headerKey, parameters.get(headerKey)).build();
                }

            }
        }
        HttpUrl url = builder.build();
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);

    }
}