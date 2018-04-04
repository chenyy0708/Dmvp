package com.chen.common.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.chen.common.rx.IRetrofitManager;
import com.chen.common.rx.RetrofitManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :提供一些三方库客户端实例
 */

@Module
public class NetModule {
    /**
     * 超时时间
     */
    private static final int TIME_OUT = 1000 * 20;
    /**
     * 域名
     */
    private String httpUrl;

    public NetModule(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    @Provides
    String provideHttpUrl() {
        return this.httpUrl;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(httpUrl)
                .build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient(Interceptor logInterceptor) {
        return new OkHttpClient.Builder()
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(logInterceptor)
                .build();
    }

    @Singleton
    @Provides
    Interceptor provideLogInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    @Singleton
    @Provides
    public IRetrofitManager provideRepositoryManager(RetrofitManager repositoryManager) {
        return repositoryManager;
    }


}
