package com.chen.common.di.component;

import android.app.Application;

import com.chen.common.rx.IRetrofitManager;
import com.chen.common.di.module.AppModule;
import com.chen.common.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :拥有此接口的实现类即可调用对应的方法拿到 Dagger 提供的对应实例
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    Application application();

    //用于管理网络请求层,以及数据缓存层
    IRetrofitManager repositoryManager();
}
