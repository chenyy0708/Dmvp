package com.wanandroid.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroid.bean.ArticleDatas;
import com.wanandroid.chen.R;

import java.util.List;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class ArticleAdapter extends BaseQuickAdapter<ArticleDatas, BaseViewHolder> {
    public ArticleAdapter(int layoutResId, List<ArticleDatas> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleDatas item) {
        helper.setText(R.id.tv_name, item.getAuthor());
    }
}
