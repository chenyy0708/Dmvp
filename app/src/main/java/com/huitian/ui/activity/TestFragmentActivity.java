package com.huitian.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.huitian.chen.R;
import com.huitian.ui.fragment.TestFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author :ChenYangYi
 * @time :2018/4/3
 * @desc :
 */

public class TestFragmentActivity extends BaseActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //获取碎片管理者
        FragmentManager mFragmentManager = getSupportFragmentManager();
//事务是不能共享的，每次用到都要重新开启一个事务，之后提交
        FragmentTransaction fragmentTransactiontwo = mFragmentManager.beginTransaction();
//参数：1.父容器   2.要替换的fragment。
        fragmentTransactiontwo.replace(R.id.fl_container, new TestFragment());
//提交事务
        fragmentTransactiontwo.commit();
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

}
