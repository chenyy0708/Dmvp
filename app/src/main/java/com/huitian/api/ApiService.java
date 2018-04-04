package com.huitian.api;


import com.huitian.bean.MeizhiData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * des:ApiService
 * Created by Chen
 * on 2017.05.22
 */
public interface ApiService {
    // 一页请求10条数据
    @GET("data/福利/" + 10 + "/{page}")
    Observable<MeizhiData> getMeizhiData(@Path("page") int page);
}
