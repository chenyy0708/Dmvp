package com.chen.common.rx;

import android.app.Activity;
import android.content.Context;

import com.chen.common.R;
import com.chen.common.app.BaseApplication;
import com.chen.common.utils.NetWorkUtils;
import com.chen.common.widget.LoadingDialog;

import io.reactivex.observers.DisposableObserver;


/**
 * des:订阅封装
 * modify by ChenYangYi
 * on 2018/4/4  修改Rxjava1  to   Rxjava2
 *
 * @author HOREN
 ********************/
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    private BaseObserver(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public BaseObserver(Context context) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), true);
    }

    protected BaseObserver(Context context, boolean showDialog) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onComplete() {
        if (showDialog) {
            LoadingDialog.cancelDialogForLoading();
        }
    }

    @Override
    protected void onStart() {
        if (showDialog) {
            try {
                LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog) {
            LoadingDialog.cancelDialogForLoading();
        }
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
