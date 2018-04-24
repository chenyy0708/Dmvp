package com.wanandroid.api;


import com.wanandroid.bean.BannerData;
import com.wanandroid.bean.BaseEntry;
import com.wanandroid.bean.HomeArticleBean;
import com.wanandroid.bean.NavigationBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * des:WanAndroid
 * Created by Chen
 * on 2017.05.22
 */
public interface WanAndroid {
    /**
     * 首页文章列表
     *
     * @param page 页码，拼接在连接中，从0开始。
     * @return 文字列表
     */
    @GET("article/list/" + "/{page}" + "/json")
    Observable<BaseEntry<HomeArticleBean>> getHomeArticleList(@Path("page") int page);


    /**
     * 首页banner
     *
     * @return Banner List
     */
    @GET("banner/json")
    Observable<BaseEntry<List<BannerData>>> getHomeBanner();

    /**
     * 导航列表
     *
     * @return Navigation List
     */
    @GET("navi/json")
    Observable<BaseEntry<List<NavigationBean>>> getNavigationData();

}
