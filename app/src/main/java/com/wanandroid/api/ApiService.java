package com.wanandroid.api;


import com.wanandroid.bean.BaseEntry;
import com.wanandroid.bean.HomeArticleBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * des:ApiService
 * Created by Chen
 * on 2017.05.22
 */
public interface ApiService {
    /**
     * 首页文章列表
     *
     * @param page 页码，拼接在连接中，从0开始。
     * @return 文字列表
     */
    @GET("article/list/" + "/{page}" + "/json")
    Observable<BaseEntry<HomeArticleBean>> getHomeArticleList(@Path("page") int page);

}
