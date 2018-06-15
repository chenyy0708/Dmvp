package com.horen.avbobo.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.horen.avbobo.R;
import com.horen.avbobo.api.Url;
import com.horen.avbobo.bean.VideoListBean;
import com.horen.avbobo.glide.GlideUtil;

import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/06/15/10:18
 * @description :
 * @github :https://github.com/chenyy0708
 */
public class AvboboAdapter extends BaseQuickAdapter<VideoListBean.DocsBean, BaseViewHolder> {
    public AvboboAdapter(int layoutResId, @Nullable List<VideoListBean.DocsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoListBean.DocsBean item) {
        GlideUtil.loadUrl(mContext, Url.SERVICE + item.getCoverData().get_$01().getLocal().getPath(), (ImageView) helper.getView(R.id.iv));

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time, item.getTime());
    }
}
