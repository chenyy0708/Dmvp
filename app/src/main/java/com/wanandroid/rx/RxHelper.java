package com.wanandroid.rx;

import com.chen.common.rx.ServerException;
import com.wanandroid.bean.BaseEntry;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author :ChenYangYi
 * @date  :2018/4/17
 * @description  : RxJava2工具类，用于线程切换等
 */
public class RxHelper {

    /**
     * 用于订阅转换，返回CompositeDisposable便于取消订阅，同意管理
     *
     * @param observable 被订阅者
     * @param observer   订阅者
     */
    public static <T> Disposable subscribe(Observable<T> observable, final Observer<T> observer) {
        return observable.subscribe(
                new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        observer.onNext(t);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        observer.onError(throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        observer.onComplete();
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        observer.onSubscribe(disposable);
                    }
                }
        );
    }

    /**
     * 线程切换
     *
     * @param <T>
     * @return
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

    /**
     * Rx优雅处理服务器返回
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseEntry<T>, T> handleResult() {
        return new ObservableTransformer<BaseEntry<T>, T>() {
            @Override
            public Observable<T> apply(Observable<BaseEntry<T>> upstream) {
                return upstream.flatMap(new Function<BaseEntry<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(BaseEntry<T> entry) throws Exception {
                        if (entry.isSuccess()) {
                            return createData(entry.getData());
                        } else {
                            return Observable.error(new ServerException(entry.getErrorMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

//    /**
//     * Rx优雅处理服务器返回
//     *
//     * @param <T>
//     * @return
//     */
//    public static <T> ObservableTransformer<BaseEntry<T>, T> handleResult() {
//        return upstream -> {
//            return upstream.flatMap(result -> {
//                        if (result.isSuccess()) {
//                            return createData(result.getData());
//                        } else {
//                            return Observable.error(new ServerException(result.getErrorCode()));
//                        }
//                    }
//            );
//        };
//    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(data);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

    }
}
