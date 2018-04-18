package com.huitian.mvp;

import com.chen.common.di.ActivityScope;
import com.chen.common.rx.IRetrofitManager;
import com.huitian.api.ApiService;
import com.huitian.bean.HomeArticleBean;
import com.huitian.rx.RxHelper;

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
