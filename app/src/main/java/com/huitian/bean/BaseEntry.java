package com.huitian.bean;

import java.io.Serializable;

/**
 * @author :ChenYangYi
 * @time :2018/4/18
 * @desc :WanAndroid Api 基类
 */
public class BaseEntry<T> implements Serializable {
    private String errorCode;
    private String errorMsg;
    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

    public boolean isSuccess() {
        return errorCode.equals("0");
    }
}
