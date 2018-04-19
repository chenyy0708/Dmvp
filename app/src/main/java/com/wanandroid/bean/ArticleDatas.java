package com.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author :ChenYangYi
 * @time :2018/4/18
 * @desc :
 */
public class ArticleDatas implements Serializable {
    private String apklink;
    private String author;
    private int chapterid;
    private String chaptername;
    private boolean collect;
    private int courseid;
    private String desc;
    private String envelopepic;
    private boolean fresh;
    private int id;
    private String link;
    private String nicedate;
    private String origin;
    private String projectlink;
    private int publishtime;
    private int superchapterid;
    private String superchaptername;
    private List<ArticleTags> tags;
    private String title;
    private int type;
    private int visible;
    private int zan;


    public void setApklink(String apklink) {
        this.apklink = apklink;
    }
    public String getApklink() {
        return apklink;
    }


    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }


    public void setChapterid(int chapterid) {
        this.chapterid = chapterid;
    }
    public int getChapterid() {
        return chapterid;
    }


    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }
    public String getChaptername() {
        return chaptername;
    }


    public void setCollect(boolean collect) {
        this.collect = collect;
    }
    public boolean getCollect() {
        return collect;
    }


    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }
    public int getCourseid() {
        return courseid;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }


    public void setEnvelopepic(String envelopepic) {
        this.envelopepic = envelopepic;
    }
    public String getEnvelopepic() {
        return envelopepic;
    }


    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }
    public boolean getFresh() {
        return fresh;
    }


    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return link;
    }


    public void setNicedate(String nicedate) {
        this.nicedate = nicedate;
    }
    public String getNicedate() {
        return nicedate;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getOrigin() {
        return origin;
    }


    public void setProjectlink(String projectlink) {
        this.projectlink = projectlink;
    }
    public String getProjectlink() {
        return projectlink;
    }


    public void setPublishtime(int publishtime) {
        this.publishtime = publishtime;
    }
    public int getPublishtime() {
        return publishtime;
    }


    public void setSuperchapterid(int superchapterid) {
        this.superchapterid = superchapterid;
    }
    public int getSuperchapterid() {
        return superchapterid;
    }


    public void setSuperchaptername(String superchaptername) {
        this.superchaptername = superchaptername;
    }
    public String getSuperchaptername() {
        return superchaptername;
    }


    public void setTags(List<ArticleTags> tags) {
        this.tags = tags;
    }
    public List<ArticleTags> getTags() {
        return tags;
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


    public void setVisible(int visible) {
        this.visible = visible;
    }
    public int getVisible() {
        return visible;
    }


    public void setZan(int zan) {
        this.zan = zan;
    }
    public int getZan() {
        return zan;
    }
}
