package com.chen.common.rx;

import android.app.Application;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import retrofit2.Retrofit;

/**
 * @author :ChenYangYi
 * @time :2018/4/3
 * @desc : 根据传入的 Class 获取对应的 Retrofit service,单例
 */
@Singleton
public class RetrofitManager implements IRetrofitManager {
    private Lazy<Retrofit> mRetrofit; // Lazy(懒加载)
    private Application mApplication;
    private HashMap<String, Object> mRetrofitServiceCache; // 保存service，防止重复创建


    @Inject
    public RetrofitManager(Lazy<Retrofit> mRetrofit, Application mApplication) {
        this.mRetrofit = mRetrofit;
        this.mApplication = mApplication;
        mRetrofitServiceCache = new HashMap<>();
    }

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        if (mRetrofitServiceCache.containsKey(service.getName())) { // 如果map集合中存在改字节码对应的service对象，直接取出
            return (T) mRetrofitServiceCache.get(service.getName());
        } else { // map中不存在
            mRetrofitServiceCache.put(service.getName(), mRetrofit.get().create(service));
            return (T) mRetrofitServiceCache.get(service.getName());
        }
    }
}
