package com.horen.avbobo.mvp.model;

import com.chen.common.di.FragmentScope;
import com.chen.common.rx.IRetrofitManager;
import com.chen.common.rx.RxSchedulers;
import com.horen.avbobo.api.Api;
import com.horen.avbobo.bean.VideoListBean;
import com.horen.avbobo.mvp.contract.AvboboContract;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 导航Model 获取数据
 */
@FragmentScope
public class AvboboModel implements AvboboContract.Model {

    private IRetrofitManager retrofitManager;

    @Inject
    public AvboboModel(IRetrofitManager retrofitManager) {
        this.retrofitManager = retrofitManager;
    }

    @Override
    public Observable<VideoListBean> getVideoList(int page, int rows) {
        return Api.getDefult().getVideoList("",page, rows)
                .compose(RxSchedulers.<VideoListBean>io_main());
    }
}
