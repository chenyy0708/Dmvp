package com.wanandroid.mvp.contract;

import android.app.Activity;

import com.chen.common.base.BaseModel;
import com.chen.common.base.BasePresenter;
import com.chen.common.base.BaseView;
import com.wanandroid.bean.NavigationBean;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description :导航Fragment 契约接口
 */

public interface NavigationContract {


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
         * 导航列表初始化
         *
         * @param data 导航列表
         */
        void setNavigationData(List<NavigationBean> data);

    }

    /**
     * Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
     */
    interface Model extends BaseModel {
        /**
         * 导航列表
         *
         * @return 导航列表
         */
        Observable<List<NavigationBean>> getNavigationData();
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

        /**
         * 导航列表
         */
        public abstract void getNavigationData();

    }

}
