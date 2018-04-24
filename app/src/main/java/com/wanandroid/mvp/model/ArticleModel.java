package com.wanandroid.mvp.model;

import com.chen.common.di.ActivityScope;
import com.chen.common.rx.IRetrofitManager;
import com.wanandroid.api.WanAndroid;
import com.wanandroid.bean.BannerData;
import com.wanandroid.bean.HomeArticleBean;
import com.wanandroid.mvp.contract.ArticleContract;
import com.wanandroid.rx.RxHelper;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @date  :2018/4/2
 * @description  :
 */
@ActivityScope
public class ArticleModel implements ArticleContract.Model {

    private IRetrofitManager retrofitManager;

    @Inject
    public ArticleModel(IRetrofitManager retrofitManager) {
        this.retrofitManager = retrofitManager;
    }

    @Override
    public Observable<HomeArticleBean> getHomeArticleList(int page) {
        return retrofitManager.obtainRetrofitService(WanAndroid.class)
                .getHomeArticleList(page)
                .compose(RxHelper.handleResult());
    }

    @Override
    public Observable<List<BannerData>> getHomeBanner() {
        return retrofitManager.obtainRetrofitService(WanAndroid.class)
                .getHomeBanner()
                .compose(RxHelper.handleResult());
    }
}
