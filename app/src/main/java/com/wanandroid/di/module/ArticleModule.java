package com.wanandroid.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chen.common.di.ActivityScope;
import com.wanandroid.chen.R;
import com.wanandroid.mvp.contract.ArticleContract;
import com.wanandroid.mvp.contract.ArticleModel;
import com.wanandroid.ui.adapter.ArticleAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

@Module
public class ArticleModule {

    private ArticleContract.View view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public ArticleModule(ArticleContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ArticleContract.View provideTestView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ArticleContract.Model provideTestModel(ArticleModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    ArticleAdapter provideArticleAdapter() {
        return new ArticleAdapter(R.layout.item_article, new ArrayList<>());
    }

    @ActivityScope
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(view.getVActivity());
    }
}
