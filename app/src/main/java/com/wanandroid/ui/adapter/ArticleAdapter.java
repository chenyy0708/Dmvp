package com.wanandroid.ui.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wanandroid.bean.ArticleDatas;
import com.wanandroid.chen.R;
import com.wanandroid.glide.GlideUtil;

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
//        GlideUtil.loadUrl(mContext,
//                "http://img1.imgtn.bdimg.com/it/u=1985628086,3753398067&fm=27&gp=0.jpg", (ImageView) helper.getView(R.id.iv_article));
    }
}
