package com.huitian.mvp;

import android.app.Activity;

import com.chen.common.base.BaseModel;
import com.chen.common.base.BaseView;
import com.huitian.bean.BaseEntry;
import com.huitian.bean.HomeArticleBean;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public interface ArticleContract {


    /**
     * 对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
     */
    interface View extends BaseView {
        /**
         * 获取View实例
         *
         * @return View
         */
        Activity getVActivity();
    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
     */
    interface Model extends BaseModel {
        /**
         * 首页文章列表
         *
         * @param page 页码，拼接在连接中，从0开始。
         * @return 文字列表
         */
        Observable<HomeArticleBean> getHomeArticleList(int page);
    }

}
