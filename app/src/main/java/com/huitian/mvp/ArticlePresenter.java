package com.huitian.mvp;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.common.base.BasePresenter;
import com.chen.common.rx.RxSubscriber;
import com.huitian.bean.ArticleDatas;
import com.huitian.bean.HomeArticleBean;
import com.huitian.ui.adapter.ArticleAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class ArticlePresenter extends BasePresenter<ArticleContract.View, ArticleContract.Model> {

    private ArticleAdapter mAdapter;


    private List<ArticleDatas> mData;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    @Inject
    public ArticlePresenter(ArticleContract.Model model, ArticleContract.View rootView, ArticleAdapter mAdapter) {
        super(model, rootView);
        this.mAdapter = mAdapter;
        this.mContext = rootView.getVActivity();
    }

    public void getData(int page) {
        mRxManager.add(mModel.getHomeArticleList(page)
                .subscribeWith(new RxSubscriber<HomeArticleBean>(mContext,true) {
                    @Override
                    protected void _onNext(HomeArticleBean articleBean) {
//                        mAdapter.setNewData(articleBean.getDatas());
                        mAdapter.setNewData(articleBean.getDatas());
                    }

                    @Override
                    protected void _onError(String message) {
                    }
                }));
    }

    public void loadData(int page) {
        mRxManager.add(mModel.getHomeArticleList(page)
                .subscribeWith(new RxSubscriber<HomeArticleBean>(mContext,true) {
                    @Override
                    protected void _onNext(HomeArticleBean articleBean) {
                        mAdapter.addData(articleBean.getDatas());
                    }

                    @Override
                    protected void _onError(String message) {
                    }
                }));
    }
}
