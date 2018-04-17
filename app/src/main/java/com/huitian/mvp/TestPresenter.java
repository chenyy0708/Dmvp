package com.huitian.mvp;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chen.common.base.BasePresenter;
import com.chen.common.rx.RxSubscriber;
import com.huitian.bean.Meizhi;
import com.huitian.bean.MeizhiData;

import java.util.List;

import javax.inject.Inject;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class TestPresenter extends BasePresenter<TestContract.View, TestContract.Model> {

    private BaseQuickAdapter mAdapter;


    private List<Meizhi> mData;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View rootView, BaseQuickAdapter mAdapter, List<Meizhi> mData) {
        super(model, rootView);
        this.mAdapter = mAdapter;
        this.mData = mData;
        this.mContext = rootView.getVActivity();
    }

    public void getData(int page) {
        mRxManager.add(mModel.getMeizhi(page)
                .subscribeWith(new RxSubscriber<MeizhiData>(mContext, true) {
                    @Override
                    protected void _onNext(MeizhiData meizhiData) {
                        mData.addAll(meizhiData.results);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                    }
                }));
        // 两种写法都可以，用于处理Rxjava2可能会引起的内存泄露问题
//        mRxManager.add(RxHelper.subscribe(mModel.getMeizhi(page), new RxSubscriber<MeizhiData>(mContext,true) {
//            @Override
//            protected void _onNext(MeizhiData meizhiData) {
//                mData.addAll(meizhiData.results);
//                mAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            protected void _onError(String message) {
//                mView.showErrorTip(message);
//            }
//        }));
    }
}
