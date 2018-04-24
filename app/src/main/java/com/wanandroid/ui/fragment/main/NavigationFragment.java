package com.wanandroid.ui.fragment.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chen.common.base.BaseFragment;
import com.chen.common.di.component.AppComponent;
import com.wanandroid.bean.NavigationBean;
import com.wanandroid.chen.R;
import com.wanandroid.di.component.DaggerNavigationComponent;
import com.wanandroid.di.module.NavigationModule;
import com.wanandroid.mvp.contract.NavigationContract;
import com.wanandroid.mvp.presenter.NavigationPresenter;
import com.wanandroid.ui.adapter.NavigationTabAdapter;
import com.wanandroid.utils.SnackbarUtils;

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
public class NavigationFragment extends BaseFragment<NavigationPresenter> implements NavigationContract.View {
    @BindView(R.id.vtb_tab_layout)
    VerticalTabLayout vtbTabLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    LinearLayoutManager linearLayoutManager;

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
        mPresenter.getNavigationData();
        vtbTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
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
    }

    @Override
    public void showErrorTip(String msg) {
        SnackbarUtils.showSnackMessage(_mActivity, msg);
    }
}
