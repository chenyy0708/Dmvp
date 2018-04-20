package com.wanandroid.glide;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author :ChenYangYi
 * @time :2018/4/20
 * @desc : Glide 工具类
 */
public class GlideUtil {

    /**
     * Glide加载图片
     * @param context 上下文
     * @param url 图片地址
     * @param iv ImaegView
     */
    public static void loadUrl(Context context, String url, ImageView iv) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .into(iv);
    }
}
