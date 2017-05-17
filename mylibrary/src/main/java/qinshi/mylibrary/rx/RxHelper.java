package qinshi.mylibrary.rx;

import android.text.TextUtils;

import qinshi.mylibrary.model.BaseModel;
import qinshi.mylibrary.utils.LogUtils;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class RxHelper {

    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseModel<T>, T> handleResult() {
        return new Observable.Transformer<BaseModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseModel<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseModel<T> result) {

                        if (TextUtils.isEmpty(result.errMsg)) {  //当错误信息是空的，就说明数据对的
                            return createData(result.data);
                        } else {
                            return Observable.error(new ServerException(result.errMsg,result.errCode));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

    /**
     * 线程切换的封装
     *
     * @param <T>
     * @return
     */

    public static <T> Observable.Transformer<BaseModel<T>, BaseModel<T>> rxSchedulerHelper() {
        return new Observable.Transformer<BaseModel<T>, BaseModel<T>>() {
            @Override
            public Observable<BaseModel<T>> call(Observable<BaseModel<T>> source) {
                return source.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }





}
