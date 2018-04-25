package com.wanandroid.ui.adapter;

import android.graphics.Color;

import com.wanandroid.bean.NavigationBean;

import java.util.List;

import q.rorbin.verticaltablayout.adapter.SimpleTabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * @author :ChenYangYi
 * @date :2018/04/24/14:43
 * @description : 导航Tab Adapter
 * @github :https://github.com/chenyy0708
 */
public class NavigationTabAdapter extends SimpleTabAdapter {
    /**
     * 导航数据
     */
    private List<NavigationBean> mDatas;

    public NavigationTabAdapter(List<NavigationBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {
        return new ITabView.TabTitle.Builder()
                .setContent(mDatas.get(position).getName())
                .setTextColor(Color.parseColor("#FFFFFF"), Color.parseColor("#666666"))
                .build();
    }
}
