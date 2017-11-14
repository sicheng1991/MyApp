package com.chimu.mylib.common;

import android.util.Log;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Rxjava操作符
 *
 * 1.create
 *
 * Created by Longwj on 2017/11/14.
 */

public class RxjavaOperator {


    /**
     * //1.创建
     *Observable.just();
     * @return
     */
    static public Observable create(){
        return Observable.create(new ObservableOnSubscribe<String>(){
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("item1");
                e.onNext("item2");
                e.onComplete();
            }
        });
    }

    /**
     * map 基本作用就是将一个 Observable 通过某种函数关系，转换为另一种 Observable
     * int 转String
     *
     * @return
     */
    static public Observable map(){
        return   Observable.fromArray(new Integer[]{3,6,9}).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer ints) throws Exception {
                return "This is result " + ints / 3;
            }
        });
    }

    /**
     * 合并
     *两两配对，也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同。
     * @return
     */
    static public Observable zip(){
        return   Observable.fromArray(new Integer[]{3,6,9,12}).zipWith(Observable.fromArray(new Integer[]{3, 6, 9}), new BiFunction<Integer,Integer,String>() {
            @Override
            public String apply(Integer o, Integer o2) throws Exception {
                return o * o2 + "";
            }
        });
    }

    /**
     * 组合
     *
     * @return
     */
    static public Observable concat(){
        return   Observable.fromArray(new Integer[]{3,6,9,12}).concatWith(Observable.fromArray(new Integer[]{31, 61, 91})).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer ints) throws Exception {
                return "This is result " + ints / 3;
            }
        });
    }

    /**
     * merge:它和 concat 的区别在于，
     * 不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送
     *
     * @return
     */
    static public Observable merge(){
        return   Observable.merge(Observable.just("1","3","6"),Observable.just("2","4","9"));
    }


    /**
     * 和concatMap差别：没有顺序
     *
     * @return
     */
    static public Observable flatMap(){
        return   Observable.fromArray(new Integer[]{3,6,9,12}).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        });
    }
    /**
     * 和flatMap差别：有顺序
     *
     * @return
     */
    static public Observable concatMap(){
        return   Observable.fromArray(new Integer[]{3,6,9,12}).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        });
    }




    /**
     * from:将数组或集合拆分成具体对象后，转换成发送这些对象的Observable
     *  fromIterable()：集合
     * @return
     */
    static public Observable from(){
        return Observable.fromArray(new String[]{"2","3","4"});
    }


    /**
     * 去重
     * @return
     */
    static public Observable distinct(){
        return Observable.just(1,2,1,2,3,4,5).distinct().map(new Function<Integer, String>() {
            @Override
            public String apply(Integer ints) throws Exception {
                return "This is result " + ints;
            }
        });
    }

    /**
     * 过滤
     * @return
     */
    static public Observable filter(){
        return Observable.just(1,2,1,2,3,4,5)
        .filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return  integer >= 3;
            }
        })
        .map(new Function<Integer, String>() {
            @Override
            public String apply(Integer ints) throws Exception {
                return "This is result " + ints;
            }
        });
    }

    /**
     *我们把 1, 2, 3, 4, 5 依次发射出来，经过 buffer 操作符，其中参数 skip 为 2， count 为 3，
     * 而我们的输出 依次是 123，345，5。显而易见，我们 buffer 的第一个参数是 count，
     * 代表最大取值，在事件足够的时候，一般都是取 count 个值，然后每次跳过 skip 个事件
     *
     * @return
     */
    static public void buffer(){
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        Log.e("ggggg", "buffer size : " + integers.size() + "\n");
                        Log.e("ggggg", "buffer value : " );
                        for (Integer i : integers) {
                            Log.e("ggggg", i + "");
                        }
                        Log.e("ggggg", "\n");
                    }
                });

    }

    /**
     * 在next之前加处理逻辑
     */
    static public void doOnNext(){
        Observable.just(1, 2, 3, 4, 5)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("ggggg", "doOnNext: " + integer + "\n");
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integers) throws Exception {
                        Log.e("ggggg", ": " + integers + "\n");

                    }
                });

    }

    /**
     * 跳过前面一部分
     */
    static public void skip(){
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integers) throws Exception {
                        Log.e("ggggg", ": " + integers + "\n");

                    }
                });
    }

    /**
     * 只要前面一部分
     */
    static public void take(){
        Observable.just(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integers) throws Exception {
                        Log.e("ggggg", ": " + integers + "\n");
                    }
                });
    }


    /**
     * 下游接受延时,只能发送Long？
     * @return
     */
    static public void timer(){
        Log.e("gggg", "timer start : " + System.currentTimeMillis() + "\n");
        Observable
                .timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e("gggg", "timer :" + aLong + " at " + System.currentTimeMillis() + "\n");
                    }
                });

    }
    /**
     * 首次在5s后发送，以后每隔3s发一次
     * @return
     */
    static public void interval(){
        Log.e("gggg", "timer start : " + System.currentTimeMillis() + "\n");
        Observable
                .interval(5,3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // interval 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e("gggg", "timer :" + aLong + " at " + System.currentTimeMillis() + "\n");
                    }
                });

    }


    /**
     * 测试
     */
    static public void test(){
//        RxjavaOperator.skip();
//        RxjavaOperator.take();
        Observable observable = RxjavaOperator.merge();
        observer(observable);
    }

    private static void observer(Observable observable) {
        //        observable.subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d("maggggg", "订阅者线程：" + Thread.currentThread().getName());
//                Log.d("maggggg", "onNext: " + integer);
//            }
//        });

        RxjavaOperator.setSubscribe(observable, new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
//                d.dispose();//切断关联，接收
                System.out.println("maggggg,onSubscribe"+d);
            }

            @Override
            public void onNext(String o) {
                // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                System.out.println("magggggonNext"+o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("maggggg"+e);
            }

            @Override
            public void onComplete() {
                System.out.println("maggggg:完成");
            }
        });
    }


    /**
     *
     * 生命周期，上游在io线程，下游在主线程
     * @param observable
     * @param observer
     * @param <T>
     */
    static public  <T> void setSubscribe(Observable<T> observable, Observer observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);

    }

}
