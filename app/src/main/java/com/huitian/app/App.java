package com.huitian.app;

import android.support.annotation.NonNull;

import com.chen.common.app.BaseApplication;
import com.chen.common.app.IApp;
import com.chen.common.di.component.AppComponent;
import com.chen.common.di.component.DaggerAppComponent;
import com.chen.common.di.module.AppModule;
import com.chen.common.di.module.NetModule;

/**
 * @author :ChenYangYi
 * @time :2018/4/3
 * @desc :
 */

public class App extends BaseApplication implements IApp {
    private AppComponent appComponent;
    public static final String SERVICE_URL = "http://gank.io/api/";

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(SERVICE_URL))
                .build();
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
