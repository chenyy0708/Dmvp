package com.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroid.bean.NavigationArticleBean;
import com.wanandroid.chen.R;
import com.wanandroid.ui.activity.webview.CommonWebViewActivity;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/04/24/16:59
 * @description : 导航列表 列表item
 * @github :https://github.com/chenyy0708
 */
public class NavigationArticleAdapter extends BaseQuickAdapter<NavigationArticleBean, BaseViewHolder> {
    public NavigationArticleAdapter(int layoutResId, @Nullable List<NavigationArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationArticleBean item) {
        helper.setText(R.id.tv_navigation_article_name, item.getTitle());
        helper.setOnClickListener(R.id.tv_navigation_article_name, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonWebViewActivity.startAction(mContext, item.getLink(), item.getTitle());
            }
        });
    }
}
