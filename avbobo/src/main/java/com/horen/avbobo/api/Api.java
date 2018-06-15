package com.horen.avbobo.api;


import android.util.SparseArray;

import com.chen.common.app.BaseApplication;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * des:retorfit api
 * Created by xsf
 * on 2016.06.15:47
 */
public class Api {
    private Retrofit retrofit;
    private AvboboService movieService;
    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(3);
    private static final int HOREN_URL = 1001;
    private static final String SERVER = "http://api.iavbobo.com";

    //构造方法私有
    private Api(String baseUrl) {
        //开启Log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtYW4iOiJYaWFvbWkiLCJicmFuZCI6IlhpYW9taSIsInN5c3RlbU5hbWUiOiJBbmRyb2lkIiwic3lzdGVtVmVyc2lvbiI6IjcuMS4xIiwidW5pcXVlIjoiZmU1ZGRkY2RiNGRlZjA3YyIsImlhdCI6MTUyOTAyNTIzNSwiZXhwIjoxNTI5MDYxMjM1fQ.BznOkahUEh62hEzTB7MAfWJG06txOJpwCJ2cYqWrDcA")
                        .build();
                return chain.proceed(build);
            }
        };


        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(BaseApplication.getAppContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cookieJar(cookieJar)
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();

        movieService = retrofit.create(AvboboService.class);
    }

    /**
     * 获取 APiService
     */
    public static AvboboService getDefult() {
        Api retrofitManager = sRetrofitManager.get(HOREN_URL);
        if (retrofitManager == null) {
            retrofitManager = new Api(SERVER);
            sRetrofitManager.put(HOREN_URL, retrofitManager);
        }
        return retrofitManager.retrofit.create(AvboboService.class);
    }
}