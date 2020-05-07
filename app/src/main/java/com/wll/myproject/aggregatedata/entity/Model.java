package com.wll.myproject.aggregatedata.entity;
/*
    Create by WLL on 2020/4/27 DATA: 14:16
*/

public class Model {
    private int viewId;
    private String title;

    public Model(int viewId, String title) {
        this.viewId = viewId;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Model{" +
                "viewId=" + viewId +
                ", title='" + title + '\'' +
                '}';
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
