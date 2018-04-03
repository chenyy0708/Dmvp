package com.huitian.di.module;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.common.di.ActivityScope;
import com.huitian.bean.Meizhi;
import com.huitian.chen.R;
import com.huitian.mvp.TestContract;
import com.huitian.mvp.TestModel;
import com.huitian.ui.adapter.UserDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

@Module
public class TestModule {

    private TestContract.View view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public TestModule(TestContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TestContract.View provideTestView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TestContract.Model provideTestModel(TestModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    BaseQuickAdapter provideUserDetailAdapter(List<Meizhi> list) {
        return new UserDetailAdapter(R.layout.item_user_detail, list);
    }

    @ActivityScope
    @Provides
    List<Meizhi> provideUserDetailList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(view.getVActivity());
    }
}
