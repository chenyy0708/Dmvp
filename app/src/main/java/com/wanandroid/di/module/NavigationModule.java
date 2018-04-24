package com.wanandroid.di.module;

import android.support.v7.widget.LinearLayoutManager;

import com.chen.common.di.ActivityScope;
import com.wanandroid.mvp.contract.NavigationContract;
import com.wanandroid.mvp.model.NavigationModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 导航 Fragment Module
 */

@Module
public class NavigationModule {

    private NavigationContract.View view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public NavigationModule(NavigationContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    NavigationContract.View provideTestView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    NavigationContract.Model provideNavigationModel(NavigationModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    LinearLayoutManager provideLayoutManager() {
        return new LinearLayoutManager(view.getVActivity());
    }
}
