package com.wll.myproject.aggregatedata.entity.wannianli;
/*
    Create by WLL on 2020/4/29 DATA: 9:55
*/

import java.io.Serializable;

public class MyWnlResult implements Serializable {
    WnlData data;

    public MyWnlResult(WnlData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyWnlResult{" +
                "data=" + data +
                '}';
    }

    public WnlData getData() {
        return data;
    }

    public void setData(WnlData data) {
        this.data = data;
    }

    public MyWnlResult() {
    }
}
