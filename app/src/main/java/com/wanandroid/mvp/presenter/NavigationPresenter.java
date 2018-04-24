package com.wanandroid.mvp.presenter;

import com.chen.common.rx.BaseObserver;
import com.wanandroid.bean.NavigationBean;
import com.wanandroid.mvp.contract.NavigationContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 导航Fragment Presenter
 */

public class NavigationPresenter extends NavigationContract.BaseNavigationPresenter {

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model    Modle获取数据层
     * @param rootView View层UI
     */
    @Inject
    public NavigationPresenter(NavigationContract.Model model, NavigationContract.View rootView) {
        super(model, rootView);
        this.mContext = rootView.getVActivity();
    }

    @Override
    public void getNavigationData() {
        mRxManager.add(mModel.getNavigationData()
                .subscribeWith(new BaseObserver<List<NavigationBean>>(mContext, false) {
                    @Override
                    protected void _onNext(List<NavigationBean> data) {
                        mView.setNavigationData(data);
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                    }
                }));
    }
}
