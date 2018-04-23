package com.wanandroid.ui.adapter;

import android.text.Html;
import android.text.TextUtils;

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
    protected void convert(BaseViewHolder helper, ArticleDatas article) {
        // 作者
        helper.setText(R.id.item_article_author, article.getAuthor());
        // 项目类型
        helper.setText(R.id.item_article_project_type, article.getSuperchaptername() + " / " + article.getChaptername());
        // 时间
        helper.setText(R.id.item_article_time, article.getNicedate());
        // 标题
        helper.setText(R.id.item_article_title, Html.fromHtml(article.getTitle()));
        helper.setGone(R.id.item_article_title, TextUtils.isEmpty(article.getTitle()) ? false : true);
        // 内容
        helper.setText(R.id.item_article_content, Html.fromHtml(article.getDesc()));
        helper.setGone(R.id.item_article_content, TextUtils.isEmpty(article.getDesc()) ? false : true);
    }
}
