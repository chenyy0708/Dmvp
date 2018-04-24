package com.wanandroid.bean;

/**
 * @author :ChenYangYi
 * @date  :2018/4/20
 * @description  : 单个轮播Bean
 */
public class BannerData {
    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;


    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public void setImagepath(String imagepath) {
        this.imagePath = imagepath;
    }
    public String getImagepath() {
        return imagePath;
    }


    public void setIsvisible(int isvisible) {
        this.isVisible = isvisible;
    }
    public int getIsvisible() {
        return isVisible;
    }


    public void setOrder(int order) {
        this.order = order;
    }
    public int getOrder() {
        return order;
    }


    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }


    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }


    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
