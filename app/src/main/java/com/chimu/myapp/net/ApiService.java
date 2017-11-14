package com.chimu.myapp.net;

import com.chimu.myapp.MyApplication;
import com.chimu.myapp.common.Constants;
import com.chimu.mylib.net.CMResponse;
import com.chimu.mylib.net.ParametersInterceptor;
import com.chimu.mylib.net.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ApiService {
    static ApiService apiService;
    static RetrofitClient client;

    public static ApiService getInstance() {
        if (apiService == null) {
            synchronized (ApiService.class) {
                if (apiService == null) {
                    apiService = new ApiService();
                }
            }
        }
        return apiService;
    }

    private ApiService() {
        client = RetrofitClient.getInstance(MyApplication.myApplication,
                Constants.URL.BASE_URL,null,new ParametersInterceptor(MyApplication.myApplication));
    }


    public interface LKSApi {
        @FormUrlEncoded
        @POST(Constants.URL.INIT)
        Observable<CMResponse> initPad(@Field("type") int dtype,
                                       @Field("mac") String mac,
                                       @Field("sign") String sign);
    }

    public void init(String mac, String sign, Observer observer) {
        setSubscribe(client.create(LKSApi.class).initPad(1, mac, sign), observer);
    }


    private <T> void setSubscribe(Observable<T> observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
//        TODO 生命周期
    }
}
