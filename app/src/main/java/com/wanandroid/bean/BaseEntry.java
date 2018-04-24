package com.wanandroid.bean;

import java.io.Serializable;

/**
 * @author :ChenYangYi
 * @date  :2018/4/18
 * @description  :WanAndroid Api 基类
 */
public class BaseEntry<T> implements Serializable {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return errorCode >= 0;
    }
}
