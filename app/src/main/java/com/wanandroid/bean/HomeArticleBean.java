package com.wanandroid.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author :ChenYangYi
 * @date  :2018/4/18
 * @description  :
 */
public class HomeArticleBean  implements Serializable{
    private int curpage;
    private List<ArticleDatas> datas;
    private int offset;
    private boolean over;
    private int pagecount;
    private int size;
    private int total;


    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }
    public int getCurpage() {
        return curpage;
    }


    public void setDatas(List<ArticleDatas> datas) {
        this.datas = datas;
    }
    public List<ArticleDatas> getDatas() {
        return datas;
    }


    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getOffset() {
        return offset;
    }


    public void setOver(boolean over) {
        this.over = over;
    }
    public boolean getOver() {
        return over;
    }


    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }
    public int getPagecount() {
        return pagecount;
    }


    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }


    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }
}
