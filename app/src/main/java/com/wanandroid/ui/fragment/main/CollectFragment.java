package com.wanandroid.ui.fragment.main;

import android.os.Bundle;
import android.widget.TextView;

import com.chen.common.base.BaseFragment;
import com.chen.common.di.component.AppComponent;
import com.wanandroid.chen.R;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author :ChenYangYi
 * @date :2018/04/24/11:14
 * @description : 收藏列表
 * @github :https://github.com/chenyy0708
 */
public class CollectFragment extends BaseFragment {
    @BindView(R.id.textView)
    TextView textView;

    public static CollectFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        CollectFragment fragment = new CollectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        textView.setText(getArguments().getString("title"));
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

}
