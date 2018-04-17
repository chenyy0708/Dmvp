package com.chen.common.rx;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author :ChenYangYi
 * @time :2018/4/17
 * @desc :
 */
public class RxManager {

    @Inject
    public RxManager() {
    }

    /**
     * 管理Observer和Subscribers订阅
     */
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * 记录发起的网络请求，防止内存泄露
     *
     * @param disposable
     */
    public void add(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /**
     * 取消所有订阅，一般和Activity/Fragment OnDestroy生命周期绑定
     */
    public void clear() {
        compositeDisposable.clear();
    }
}
