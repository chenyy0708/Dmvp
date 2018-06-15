package com.horen.avbobo.api;

import com.horen.avbobo.bean.VideoListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author :ChenYangYi
 * @date :2018/06/15/10:05
 * @description :
 * @github :https://github.com/chenyy0708
 */
public interface AvboboService {

    @GET("/searchXiaoVideo")
    Observable<VideoListBean> getVideoList(@Query("keyword") String keyword,@Query("page") int page, @Query("limit") int limit);
}
