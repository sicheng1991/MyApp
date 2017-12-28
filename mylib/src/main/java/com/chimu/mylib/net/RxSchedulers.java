package com.chimu.mylib.net;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by longwj on 2017/12/28 0028.
 */

public class RxSchedulers {

    /**
     * io线程到main线程
     *
     * @param <T>
     * @return
     */

    public static <T> ObservableTransformer<T, T> io_main() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }

}
