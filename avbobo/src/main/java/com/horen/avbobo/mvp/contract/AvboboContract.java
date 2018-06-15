package com.horen.avbobo.mvp.contract;

import android.app.Activity;

import com.chen.common.base.BaseModel;
import com.chen.common.base.BasePresenter;
import com.chen.common.base.BaseView;
import com.horen.avbobo.bean.VideoListBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description :导航Fragment 契约接口
 */

public interface AvboboContract {


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

        RefreshLayout getRefresh();

        void getTokenSuccess(String token);

        void getVideoListSuccess(List<VideoListBean.DocsBean> mData);
    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
     */
    interface Model extends BaseModel {
        Observable<VideoListBean> getVideoList(int page, int rows);
    }

    /**
     * NavigationPresenter
     */
    abstract class BaseNavigationPresenter extends BasePresenter<View, Model> {
        /**
         * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
         *
         * @param model
         * @param rootView
         */
        public BaseNavigationPresenter(Model model, View rootView) {
            super(model, rootView);
        }

        public abstract void getToken();

        /**
         * 加载更多
         */
        public abstract void loadData();

        /**
         * 加载数据
         */
        public abstract void getData();

    }

}
