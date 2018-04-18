package com.huitian.app;

import android.support.annotation.NonNull;

import com.chen.common.app.BaseApplication;
import com.chen.common.app.IApp;
import com.chen.common.di.component.AppComponent;
import com.chen.common.di.component.DaggerAppComponent;
import com.chen.common.di.module.AppModule;
import com.chen.common.di.module.NetModule;
import com.huitian.chen.R;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author :ChenYangYi
 * @time :2018/4/3
 * @desc :
 */

public class App extends BaseApplication implements IApp {
    private AppComponent appComponent;
    /**
     * WanAndroid 开放Api
     * http://www.wanandroid.com/blog/show/2
     */
    public static final String SERVICE_URL = "http://www.wanandroid.com/";

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new MaterialHeader(context).setColorSchemeColors(R.color.colorPrimary);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
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
