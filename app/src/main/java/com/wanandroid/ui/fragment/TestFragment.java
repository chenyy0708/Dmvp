package com.wanandroid.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.common.base.BaseFragment;
import com.chen.common.di.component.AppComponent;
import com.wanandroid.chen.R;
import com.wanandroid.mvp.ArticleContract;
import com.wanandroid.mvp.ArticlePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class TestFragment extends BaseFragment<ArticlePresenter> implements ArticleContract.View {
    @Inject
    BaseQuickAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_article_list;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mPresenter.getData();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }



    @Override
    public Activity getVActivity() {
        return getActivity();
    }

    @Override
    public SmartRefreshLayout getRefreshLayout() {
        return null;
    }

}
