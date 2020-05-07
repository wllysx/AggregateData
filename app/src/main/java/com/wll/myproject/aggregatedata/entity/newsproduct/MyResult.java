package com.wll.myproject.aggregatedata.entity.newsproduct;
/*
    Create by WLL on 2020/4/27 DATA: 17:20
*/

import java.io.Serializable;
import java.util.List;

public class MyResult implements Serializable {
    String stat;
    List<Product> data;

    public MyResult() {
    }

    public MyResult(String stat, List<Product> data) {
        this.stat = stat;
        this.data = data;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
