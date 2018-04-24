package com.wanandroid.bean;

import java.io.Serializable;

/**
 * @author :ChenYangYi
 * @date  :2018/4/18
 * @description :
 */
public class ArticleTags implements Serializable {
    private String name;
    private String url;


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }


    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
