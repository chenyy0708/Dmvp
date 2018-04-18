package com.chen.common.utils;

import android.content.Context;

import com.chen.common.app.IApp;
import com.chen.common.di.component.AppComponent;


/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :常用的方法工具类
 */

public class CUtils {

    /**
     * 获取APPComponent，在View中注入
     *
     * @param context 上下文
     * @return APPComponent
     */
    public static AppComponent obtainAppComponentFromContext(Context context) {
        Preconditions.checkNotNull(context, "%s cannot be null", Context.class.getName());
        Preconditions.checkState(context.getApplicationContext() instanceof IApp, "Application does not implements App");
        return ((IApp) context.getApplicationContext()).getAppComponent();
    }




}
