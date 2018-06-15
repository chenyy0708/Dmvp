package com.horen.avbobo.mvp.presenter;

import com.chen.common.rx.BaseObserver;
import com.horen.avbobo.adapter.AvboboAdapter;
import com.horen.avbobo.bean.VideoListBean;
import com.horen.avbobo.mvp.contract.AvboboContract;

import javax.inject.Inject;

/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 导航Fragment Presenter
 */

public class AvboboPresenter extends AvboboContract.BaseNavigationPresenter {

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model    Modle获取数据层
     * @param rootView View层UI
     */

    /**
     * 加载页码
     */
    private int page = 1;
    private int limit = 10;

    private AvboboAdapter mAdapter;

    @Inject
    public AvboboPresenter(AvboboContract.Model model, AvboboContract.View rootView, AvboboAdapter mAdapter) {
        super(model, rootView);
        this.mContext = rootView.getVActivity();
        this.mAdapter = mAdapter;
    }

    @Override
    public void getToken() {

    }

    @Override
    public void loadData() {
        page++;
        mRxManager.add(mModel.getVideoList(page, limit)
                .subscribeWith(new BaseObserver<VideoListBean>() {
                    @Override
                    protected void _onNext(VideoListBean videoListBean) {
                        mAdapter.addData(videoListBean.getDocs());
                        mView.getRefresh().finishLoadMore();
                    }

                    @Override
                    protected void _onError(String message) {
                        page--;
                    }
                }));
    }

    @Override
    public void getData() {
        // 页码置为初始值 10
        page = 1;
        mRxManager.add(mModel.getVideoList(page, limit)
                .subscribeWith(new BaseObserver<VideoListBean>() {
                    @Override
                    protected void _onNext(VideoListBean videoListBean) {
                        mAdapter.setNewData(videoListBean.getDocs());
                        mView.getRefresh().finishRefresh();
                    }

                    @Override
                    protected void _onError(String message) {
                    }
                }));
    }


}
