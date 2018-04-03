package com.chen.common.app;

import android.support.annotation.NonNull;

import com.chen.common.di.component.AppComponent;
import com.chen.common.di.component.DaggerAppComponent;
import com.chen.common.di.module.AppModule;
import com.chen.common.di.module.NetModule;


/**
 * Created by Chen on 2017/4/17.
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
