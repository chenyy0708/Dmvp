package com.chen.common.base;

import android.content.Context;

import com.chen.common.rx.RxManager;
import com.chen.common.utils.Preconditions;

import javax.inject.Inject;

/**
 * des:基类presenter
 * Created by xsf
 * on 2016.07.11:55
 */
public abstract class BasePresenter<T, E> {
    public Context mContext;
    public E mModel;
    public T mView;
    @Inject
    public RxManager mRxManager;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    public BasePresenter(E model, T rootView) {
        Preconditions.checkNotNull(model, "%s cannot be null", BaseModel.class.getName());
        Preconditions.checkNotNull(rootView, "%s cannot be null", BaseView.class.getName());
        this.mModel = model;
        this.mView = rootView;
        onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
        mRxManager.clear(); // 清除网络请求
    }
}
