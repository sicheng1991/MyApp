package com.chimu.mylib.net;


import com.chimu.mylib.bean.Bean;
import com.chimu.mylib.bean.InfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by longwj on 2017/3/1.
 */

public class HttpUtil {
    private static HttpUtil mHttpUtil;
    //构造单例
    private HttpUtil(){}

    public static synchronized HttpUtil getInstence(){
        if (mHttpUtil == null) {
            synchronized (HttpUtil.class) {
                if (mHttpUtil == null)
                    mHttpUtil = new HttpUtil();
            }
        }
        return mHttpUtil;
    }

    /**
     * 创建API接口
     */
    public interface InfoRes{
        //version=1&action=getownerinfo&cityId=2&model=android
         //具体写法参见博客;http://www.jianshu.com/p/7687365aa946
        //version=1&action=getownerinfo&{cityId}&model=android"
        @GET("tools/mockapi/6385/test1")
        Observable<InfoBean> getInfo(@Query("version") String version,
                                     @Query("action") String action,
                                     @Query("cityId") String cityId,
                                     @Query("model") String model);

        @GET("tools/mockapi/6385/test1")
        Observable<Bean> getTest();
    }

}
