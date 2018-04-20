package com.wanandroid.mvp.contract;

import android.support.annotation.NonNull;

import com.chen.common.rx.BaseObserver;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanandroid.bean.BannerData;
import com.wanandroid.bean.HomeArticleBean;
import com.wanandroid.ui.adapter.ArticleAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class ArticlePresenter extends ArticleContract.BaseArticlePresenter {

    /**
     * 首页Adapter
     */
    private ArticleAdapter mAdapter;

    /**
     * 加载页码
     */
    private int page;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model    Modle获取数据层
     * @param rootView View层UI
     */
    @Inject
    public ArticlePresenter(ArticleContract.Model model, ArticleContract.View rootView, ArticleAdapter mAdapter) {
        super(model, rootView);
        this.mAdapter = mAdapter;
        this.mContext = rootView.getVActivity();
        initRefreshListener();
    }

    /**
     * 加载第一页数据
     */
    @Override
    public void getData() {
        // 页码置为初始值 10
        page = 10;
        mRxManager.add(mModel.getHomeArticleList(page)
                .subscribeWith(new BaseObserver<HomeArticleBean>(mContext, false) {
                    @Override
                    protected void _onNext(HomeArticleBean articleBean) {
                        mAdapter.setNewData(articleBean.getDatas());
                        mView.getRefreshLayout().finishRefresh();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                        mView.getRefreshLayout().finishRefresh();
                    }
                }));
    }

    /**
     * 首页轮播图
     */
    @Override
    public void getBanner() {
        mRxManager.add(mModel.getHomeBanner()
                .subscribeWith(new BaseObserver<List<BannerData>>(mContext, false) {
                    @Override
                    protected void _onNext(List<BannerData> bannerData) {
                        mView.setBanner(bannerData);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                    }
                }));
    }

    /**
     * 加载下一页数据
     */
    @Override
    public void loadData() {
        // 加载下10页数据
        page += 10;
        mRxManager.add(mModel.getHomeArticleList(page)
                .subscribeWith(new BaseObserver<HomeArticleBean>(mContext, false) {
                    @Override
                    protected void _onNext(HomeArticleBean articleBean) {
                        mAdapter.addData(articleBean.getDatas());
                        mView.getRefreshLayout().finishLoadMore();
                    }

                    @Override
                    protected void _onError(String message) {
                        // 加载失败，页码减去10，防止遗漏数据
                        page -= 10;
                        mView.showErrorTip(message);
                        mView.getRefreshLayout().finishLoadMore();
                    }
                }));
    }


    /**
     * 刷新监听
     */
    private void initRefreshListener() {
        // 设置加载监听
        mView.getRefreshLayout().setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                // 加载更多
                loadData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                // 刷新数据
                getData();
                // 轮播图
                getBanner();
            }
        });
    }
}
