package com.horen.avbobo.di.module;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chen.common.di.FragmentScope;
import com.horen.avbobo.R;
import com.horen.avbobo.adapter.AvboboAdapter;
import com.horen.avbobo.bean.VideoListBean;
import com.horen.avbobo.mvp.contract.AvboboContract;
import com.horen.avbobo.mvp.model.AvboboModel;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 导航 Fragment Module
 */

@Module
public class AvboboModule {

    private AvboboContract.View view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public AvboboModule(AvboboContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    AvboboContract.View provideTestView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    AvboboContract.Model provideNavigationModel(AvboboModel model) {
        return model;
    }

    @FragmentScope
    @Provides
    AvboboAdapter provideArticleAdapter() {
        return new AvboboAdapter(R.layout.item_avbobo, new ArrayList<VideoListBean.DocsBean>());
    }

    @FragmentScope
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
    }
}
