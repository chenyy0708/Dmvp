package com.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroid.bean.NavigationBean;
import com.wanandroid.chen.R;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/04/24/16:17
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class NavigationAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {

    public NavigationAdapter(int layoutResId, @Nullable List<NavigationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {
        helper.setText(R.id.tv_navigation_name, item.getName());
    }
}
