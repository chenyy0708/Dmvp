package com.wanandroid.ui.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;

import com.chen.common.base.BaseFragment;
import com.chen.common.di.component.AppComponent;
import com.wanandroid.bean.NavigationBean;
import com.wanandroid.chen.R;
import com.wanandroid.di.component.DaggerNavigationComponent;
import com.wanandroid.di.module.NavigationModule;
import com.wanandroid.mvp.contract.NavigationContract;
import com.wanandroid.mvp.presenter.NavigationPresenter;
import com.wanandroid.ui.adapter.NavigationAdapter;
import com.wanandroid.ui.adapter.NavigationTabAdapter;
import com.wanandroid.ui.fragment.IFragment;
import com.wanandroid.utils.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * @author :ChenYangYi
 * @date :2018/04/24/13:57
 * @description : 导航Fragment
 * @github :https://github.com/chenyy0708
 */
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View, IFragment {
    @BindView(R.id.vtb_tab_layout)
    VerticalTabLayout vtbTabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    LinearLayoutManager linearLayoutManager;
    private NavigationAdapter navigationAdapter;
    private LinearSmoothScroller smoothScroller;

    /**
     * 是否点击了tab
     */
    private boolean isSelectTab = false;

    /**
     * 是否手动滚动RecycleView
     */
    private boolean isScrollRv = false;


    public static NavigationFragment newInstance() {
        Bundle args = new Bundle();
        NavigationFragment fragment = new NavigationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(linearLayoutManager);
        navigationAdapter = new NavigationAdapter(R.layout.item_navigation_rv, new ArrayList<>());
        recyclerView.setAdapter(navigationAdapter);
        mPresenter.getNavigationData();
        initTabRvListener();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerNavigationComponent.builder()
                .appComponent(appComponent)
                .navigationModule(new NavigationModule(this))
                .build()
                .inject(this);
    }

    @Override
    public Activity getVActivity() {
        return null;
    }

    @Override
    public void setNavigationData(List<NavigationBean> data) {
        vtbTabLayout.setTabAdapter(new NavigationTabAdapter(data));
        vtbTabLayout.setTabSelected(0);
        navigationAdapter.replaceData(data);
    }

    @Override
    public void showErrorTip(String msg) {
        SnackbarUtils.showSnackMessage(_mActivity, msg);
    }

    /**
     * tabLayout，Rv监听
     */
    private void initTabRvListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                // RecycleView正常滚动,非点tab滚动
                if (!isSelectTab) {
                    isScrollRv = true;
                    //获取第一个可见view的位置
                    int firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    vtbTabLayout.setTabSelected(firstItemPosition);
                    //
                } else {
                    // RecyclerView被点击tab滚动到指定位置,不需要任何操作
                    isSelectTab = false;
                }
            }
        });
        // 用于RecycleView平滑滑动到指定位置
        smoothScroller = new LinearSmoothScroller(_mActivity) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        vtbTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                // 手动滚动RecycleView
                if (isScrollRv) {
                    isScrollRv = false;
                } else {
                    // 点击tab滚动RecycleView
                    isSelectTab = true;
                    smoothScroller.setTargetPosition(i);
                    linearLayoutManager.startSmoothScroll(smoothScroller);
                }
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    @Override
    public void jumpToRVTop() {
        // 点击tab滚动RecycleView
        isSelectTab = true;
        smoothScroller.setTargetPosition(0);
        linearLayoutManager.startSmoothScroll(smoothScroller);
        vtbTabLayout.setTabSelected(0);
    }
}
