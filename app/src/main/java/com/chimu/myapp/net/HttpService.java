package com.chimu.myapp.net;


import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/3/28.
 */

public class HttpService {
    private static ApiService apiService;

    static {
        if (apiService == null) {
            synchronized (HttpService.class) {
                if (apiService == null) {
                    apiService = ApiService.getInstance();
                }
            }
        }
    }
    public static void init(String mac, String sign, Observer observer) {
        apiService.init(mac, sign, observer);
    }

}
