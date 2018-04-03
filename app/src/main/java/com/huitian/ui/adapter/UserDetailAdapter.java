package com.huitian.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huitian.bean.Meizhi;
import com.huitian.chen.R;

import java.util.List;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class UserDetailAdapter extends BaseQuickAdapter<Meizhi, BaseViewHolder> {
    public UserDetailAdapter(int layoutResId, List<Meizhi> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Meizhi item) {
        helper.setText(R.id.tv_name, item.getUrl());
    }
}
