package com.wanandroid.mvp.contract;

import android.app.Activity;

import com.chen.common.base.BaseModel;
import com.chen.common.base.BasePresenter;
import com.chen.common.base.BaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wanandroid.bean.BannerData;
import com.wanandroid.bean.HomeArticleBean;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @date  :2018/4/2
 * @description  :
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

        /**
         * 首页轮播图
         *
         * @param bannerData 轮播列表
         */
        void setBanner(List<BannerData> bannerData);

        /**
         * 在P中获取View的刷新控件
         *
         * @return 刷新控件
         */
        SmartRefreshLayout getRefreshLayout();
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

        /**
         * 首页轮播图
         *
         * @return 轮播列表
         */
        Observable<List<BannerData>> getHomeBanner();
    }

    /**
     * HomePreseneter
     */
    abstract class BaseArticlePresenter extends BasePresenter<View, Model> {

        /**
         * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
         *
         * @param model
         * @param rootView
         */
        public BaseArticlePresenter(Model model, View rootView) {
            super(model, rootView);
        }

        /**
         * 加载更多
         */
        public abstract void loadData();

        /**
         * 加载数据
         */
        public abstract void getData();

        /**
         * 轮播图
         */
        public abstract void getBanner();

    }

}
