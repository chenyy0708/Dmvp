package com.huitian.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.huitian.chen.R;
import com.huitian.di.component.DaggerArticleComponent;
import com.huitian.di.module.ArticleModule;
import com.huitian.mvp.ArticleContract;
import com.huitian.mvp.ArticlePresenter;
import com.huitian.ui.adapter.ArticleAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc : 首页
 */

public class ArticleActivity extends BaseActivity<ArticlePresenter> implements ArticleContract.View {
    @Inject
    ArticleAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mPresenter.getData(5);
        // test
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.loadData(5);
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getData(5);
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerArticleComponent.builder()
                .appComponent(appComponent)
                .articleModule(new ArticleModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }


    @Override
    public Activity getVActivity() {
        return this;
    }

}
