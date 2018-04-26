package com.chen.common.rx;

import com.chen.common.R;
import com.chen.common.app.BaseApplication;
import com.chen.common.utils.NetWorkUtils;

import io.reactivex.observers.DisposableObserver;


/**
 * des:订阅封装
 * modify by ChenYangYi
 * on 2018/4/4  修改Rxjava1  to   Rxjava2
 *
 * @author HOREN
 ********************/
public abstract class BaseObserver<T> extends DisposableObserver<T> {
    protected BaseObserver() {
    }
    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            _onError(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器 得到自定义Error，取得失败信息
        else if (e instanceof ServerException) {
            ServerException err = (ServerException) e;
            _onError(err.getMessage());
        }
        //其它
        else {
            _onError(BaseApplication.getAppContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
