package com.wanandroid.mvp.model;

import com.chen.common.di.ActivityScope;
import com.chen.common.rx.IRetrofitManager;
import com.wanandroid.api.WanAndroid;
import com.wanandroid.bean.NavigationBean;
import com.wanandroid.mvp.contract.NavigationContract;
import com.wanandroid.rx.RxHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 导航Model 获取数据
 */
@ActivityScope
public class NavigationModel implements NavigationContract.Model {

    private IRetrofitManager retrofitManager;

    @Inject
    public NavigationModel(IRetrofitManager retrofitManager) {
        this.retrofitManager = retrofitManager;
    }


    @Override
    public Observable<List<NavigationBean>> getNavigationData() {
        return retrofitManager.obtainRetrofitService(WanAndroid.class)
                .getNavigationData()
                .compose(RxHelper.handleResult());
    }
}
