package com.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date :2018/04/24/14:30
 * @description : 导航 每个类别Bean数据
 * @github :https://github.com/chenyy0708
 */
public class NavigationBean implements Serializable {
    private String cid;
    private String name;
    private List<NavigationArticleBean> articles;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NavigationArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<NavigationArticleBean> articles) {
        this.articles = articles;
    }
}
