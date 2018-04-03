package com.huitian.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.huitian.chen.R;

import butterknife.Bind;

/**
 * @author :ChenYangYi
 * @time :2018/4/3
 * @desc :
 */

public class TestFragmentActivity extends BaseActivity {
    @Bind(R.id.fl_container)
    FrameLayout flContainer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }
}
