package com.chen.common.rx;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava2调度管理
 * modify by ChenYangYi
 * on 2018/4/4  修改Rxjava1  to   Rxjava2
 */
public class RxSchedulers {

    /**
     * 子线程运行，主线程回调
     *
     * @param <T> 服务器Bean对象
     * @return ObservableTransformer用于compress操作符线程转换
     */
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
