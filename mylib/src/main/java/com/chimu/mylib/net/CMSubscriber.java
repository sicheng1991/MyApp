package com.chimu.mylib.net;

import android.text.TextUtils;

import com.chimu.mylib.util.GsonUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * CMSubscriber
 */
public abstract class CMSubscriber<T> implements Observer<T> {

    private boolean isNeedCahe;

    public CMSubscriber() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        CMResponse lksResponse = null;
        try {
            lksResponse = (CMResponse) t;
        } catch (Exception e) {
            if (null == lksResponse) {
                throw new IllegalArgumentException("传入的类型不匹配！请传入LKSResponse");
            }
        }
        if ("0".equals(lksResponse.getErrorno())) {
            if (null==lksResponse.getData()|| TextUtils.isEmpty(lksResponse.getData().toString())) {
                onSuccess("");
            } else {
                if (lksResponse.getData() instanceof String) {
                    onSuccess(lksResponse.getData().toString());
                } else {
                    onSuccess(GsonUtils.getJsonString(lksResponse.getData()));
                }
            }
        } else {
            onFail(lksResponse.getErrormsg());
        }
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }


    @Override
    public void onComplete() {
    }

    /**
     * 连接错误
     *
     * @param e
     */
    public abstract void onError(ExceptionHandle.ResponeThrowable e);

    /**
     * 服务器错误
     *
     * @param s
     */
    public abstract void onFail(String s);

    /**
     * 成功
     *
     * @param result
     */
    public abstract void onSuccess(String result);

}
