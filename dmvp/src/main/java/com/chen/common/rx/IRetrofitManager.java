package com.chen.common.rx;

/**
 * @author :ChenYangYi
 * @time :2018/4/3
 * @desc : Retrofit管理，用于获取service接口实现类
 */

public interface IRetrofitManager {
    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(Class<T> service);
}
