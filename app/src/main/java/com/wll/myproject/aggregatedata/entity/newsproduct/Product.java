package com.wll.myproject.aggregatedata.entity.newsproduct;
/*
    Create by WLL on 2020/4/27 DATA: 17:21
*/

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Product implements Serializable {
    String uniquekey;
    String title;
    String date;
    String category;

    @JSONField(name = "author_name")
    String authorName;
    String url;

    @JSONField(name = "thumbnail_pic_s")
    String thumbnailPicS;

    @JSONField(name = "thumbnail_pic_s02")
    String thumbnailPicS02;

    @JSONField(name = "thumbnail_pic_s03")
    String getThumbnailPicS023;

    public Product( ) {
    }

    public Product(String uniquekey, String title, String date, String category, String authorName, String url, String thumbnailPicS, String thumbnailPicS02, String getThumbnailPicS023) {
        this.uniquekey = uniquekey;
        this.title = title;
        this.date = date;
        this.category = category;
        this.authorName = authorName;
        this.url = url;
        this.thumbnailPicS = thumbnailPicS;
        this.thumbnailPicS02 = thumbnailPicS02;
        this.getThumbnailPicS023 = getThumbnailPicS023;
    }

    @Override
    public String toString() {
        return "Product{" +
                "uniquekey='" + uniquekey + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", authorName='" + authorName + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailPicS='" + thumbnailPicS + '\'' +
                ", thumbnailPicS02='" + thumbnailPicS02 + '\'' +
                ", getThumbnailPicS023='" + getThumbnailPicS023 + '\'' +
                '}';
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailPicS() {
        return thumbnailPicS;
    }

    public void setThumbnailPicS(String thumbnailPicS) {
        this.thumbnailPicS = thumbnailPicS;
    }

    public String getThumbnailPicS02() {
        return thumbnailPicS02;
    }

    public void setThumbnailPicS02(String thumbnailPicS02) {
        this.thumbnailPicS02 = thumbnailPicS02;
    }

    public String getGetThumbnailPicS023() {
        return getThumbnailPicS023;
    }

    public void setGetThumbnailPicS023(String getThumbnailPicS023) {
        this.getThumbnailPicS023 = getThumbnailPicS023;
    }
}
