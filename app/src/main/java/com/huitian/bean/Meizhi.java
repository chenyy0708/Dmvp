package com.huitian.bean;

import java.util.List;

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class Meizhi {

    /**
     * _id : 58e74685421aa90d6bc75ad9
     * createdAt : 2017-04-07T15:57:57.940Z
     * desc : RemoteViews详细解释
     * publishedAt : 2017-04-10T12:11:14.794Z
     * source : web
     * type : Android
     * url : http://www.haotianyi.win/2017/04/07/view/RemoteViews%E8%AF%A6%E7%BB%86%E8%A7%A3%E9%87%8A/
     * used : true
     * who : HaoTianYi
     * images : ["http://img.gank.io/74960f8f-6cb1-4c31-afc8-7d327254d614"]
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
