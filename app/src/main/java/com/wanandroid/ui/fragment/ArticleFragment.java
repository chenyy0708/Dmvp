package com.wanandroid.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.common.base.BaseFragment;
import com.chen.common.di.component.AppComponent;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wanandroid.bean.BannerData;
import com.wanandroid.chen.R;
import com.wanandroid.di.component.DaggerArticleComponent;
import com.wanandroid.di.module.ArticleModule;
import com.wanandroid.glide.GlideUtil;
import com.wanandroid.mvp.contract.ArticleContract;
import com.wanandroid.mvp.presenter.ArticlePresenter;
import com.wanandroid.ui.activity.webview.CommonWebViewActivity;
import com.wanandroid.ui.adapter.ArticleAdapter;
import com.wanandroid.utils.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * @author :ChenYangYi
 * @date :2018/4/2
 * @description : 首页
 */

public class ArticleFragment extends BaseFragment<ArticlePresenter> implements ArticleContract.View, BGABanner.Delegate<ImageView, BannerData>, BGABanner.Adapter<ImageView, BannerData> {
    @Inject
    ArticleAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private BGABanner mBanner;

    public static ArticleFragment newInstance() {
        Bundle args = new Bundle();
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_article;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        // 添加RV头布局，设置Adapter
        initRVAndHeader();
        // 获取首页数据
        refreshLayout.autoRefresh();
    }

    /**
     * 添加RV头布局，设置Adapter
     */
    private void initRVAndHeader() {
        //add head banner
        @SuppressLint("InflateParams") LinearLayout mHeaderGroup = ((LinearLayout) LayoutInflater.from(_mActivity).inflate(R.layout.header_home_banner, null));
        mBanner = mHeaderGroup.findViewById(R.id.banner);
        mHeaderGroup.removeView(mBanner);
        mBanner.setAdapter(this);
        mBanner.setDelegate(this);
        mAdapter.addHeaderView(mBanner);
        recyclerView.setLayoutManager(mLayoutManager);
        // 全局Adapter，由Module提供  跟Activity生命周期一样
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(mAdapter);
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
        SnackbarUtils.showSnackMessage(_mActivity, msg);
    }


    @Override
    public Activity getVActivity() {
        return getActivity();
    }

    @Override
    public void setBanner(List<BannerData> bannerData) {
        ArrayList<String> mTips = new ArrayList<>();
        // 提取文字
        for (BannerData bannerDatum : bannerData) {
            mTips.add(bannerDatum.getTitle());
        }
        mBanner.setData(bannerData, mTips);
        mBanner.setAutoPlayAble(true);
        mBanner.startAutoPlay();
    }

    @Override
    public SmartRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable BannerData model, int position) {
        assert model != null;
        CommonWebViewActivity.startAction(_mActivity, model.getUrl(), model.getTitle());
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable BannerData model, int position) {
        assert model != null;
        GlideUtil.loadUrl(_mActivity, model.getImagepath(), itemView);
    }

    /**
     * 滚动RV顶部
     */
    public void jumpToRVTop() {
        recyclerView.smoothScrollToPosition(0);
    }
}
