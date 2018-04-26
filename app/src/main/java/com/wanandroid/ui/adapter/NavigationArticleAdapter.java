package com.wanandroid.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.allen.library.SuperButton;
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

    /**
     * 颜色集合
     */
    private String[] mColors;

    public NavigationArticleAdapter(int layoutResId, @Nullable List<NavigationArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationArticleBean item) {
        mColors = mContext.getResources().getStringArray(R.array.tab_colors);
        SuperButton superButton = helper.getView(R.id.tv_navigation_article_name);
        // 设置边框颜色
        superButton.setText(item.getTitle());
        int i = (int) (Math.random() * mColors.length);
        String mColor = mColors[i];
        superButton.setShapeStrokeColor(Color.parseColor(mColor)).setUseShape();
        helper.setOnClickListener(R.id.tv_navigation_article_name, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonWebViewActivity.startAction(mContext, item.getLink(), item.getTitle());
            }
        });
    }
}
