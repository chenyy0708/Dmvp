package com.huitian.mvp;

import com.chen.common.di.ActivityScope;
import com.chen.common.rx.IRetrofitManager;
import com.huitian.api.ApiService;
import com.huitian.bean.MeizhiData;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */
@ActivityScope
public class TestModel implements TestContract.Model {

    private IRetrofitManager retrofitManager;

    @Inject
    public TestModel(IRetrofitManager retrofitManager) {
        this.retrofitManager = retrofitManager;
    }

    @Override
    public Observable<MeizhiData> getMeizhi(int page) {
        return retrofitManager.obtainRetrofitService(ApiService.class)
                .getMeizhiData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
