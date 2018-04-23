package com.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author :ChenYangYi
 * @time :2018/4/18
 * @desc :
 */
public class ArticleDatas implements Serializable {
    private String apkLink;
    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private int id;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private String publishTime;
    private int superChapterId;
    private String superChapterName;
    private List<ArticleTags> tags;
    private String title;
    private int type;
    private int visible;
    private int zan;


    public void setApklink(String apklink) {
        this.apkLink = apklink;
    }
    public String getApklink() {
        return apkLink;
    }


    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }


    public void setChapterid(int chapterid) {
        this.chapterId = chapterid;
    }
    public int getChapterid() {
        return chapterId;
    }


    public void setChaptername(String chaptername) {
        this.chapterName = chaptername;
    }
    public String getChaptername() {
        return chapterName;
    }


    public void setCollect(boolean collect) {
        this.collect = collect;
    }
    public boolean getCollect() {
        return collect;
    }


    public void setCourseid(int courseid) {
        this.courseId = courseid;
    }
    public int getCourseid() {
        return courseId;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }


    public void setEnvelopepic(String envelopepic) {
        this.envelopePic = envelopepic;
    }
    public String getEnvelopepic() {
        return envelopePic;
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
        this.niceDate = nicedate;
    }
    public String getNicedate() {
        return niceDate;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getOrigin() {
        return origin;
    }


    public void setProjectlink(String projectlink) {
        this.projectLink = projectlink;
    }
    public String getProjectlink() {
        return projectLink;
    }


    public void setPublishtime(String publishtime) {
        this.publishTime = publishtime;
    }
    public String getPublishtime() {
        return publishTime;
    }


    public void setSuperchapterid(int superchapterid) {
        this.superChapterId = superchapterid;
    }
    public int getSuperchapterid() {
        return superChapterId;
    }


    public void setSuperchaptername(String superchaptername) {
        this.superChapterName = superchaptername;
    }
    public String getSuperchaptername() {
        return superChapterName;
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
