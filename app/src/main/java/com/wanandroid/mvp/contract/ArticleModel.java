package com.wanandroid.mvp.contract;

import com.chen.common.di.ActivityScope;
import com.chen.common.rx.IRetrofitManager;
import com.wanandroid.api.ApiService;
import com.wanandroid.bean.HomeArticleBean;
import com.wanandroid.rx.RxHelper;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
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
        return retrofitManager.obtainRetrofitService(ApiService.class)
                .getHomeArticleList(page)
                .compose(RxHelper.handleResult());
    }
}
