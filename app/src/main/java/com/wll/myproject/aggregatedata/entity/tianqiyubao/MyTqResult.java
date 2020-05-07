package com.wll.myproject.aggregatedata.entity.tianqiyubao;
/*
    Create by WLL on 2020/4/28 DATA: 15:44
*/

import java.io.Serializable;
import java.util.List;

public class MyTqResult implements Serializable {
    String city;
    RealTime realTime;
    List<FutureTQ> future;

    public MyTqResult() {

    }

    public MyTqResult(String city, RealTime realTime, List<FutureTQ> future) {
        this.city = city;
        this.realTime = realTime;
        this.future = future;
    }

    @Override
    public String toString() {
        return "MyTqResult{" +
                "city='" + city + '\'' +
                ", realTime=" + realTime +
                ", future=" + future +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RealTime getRealTime() {
        return realTime;
    }

    public void setRealTime(RealTime realTime) {
        this.realTime = realTime;
    }

    public List<FutureTQ> getFuture() {
        return future;
    }

    public void setFuture(List<FutureTQ> future) {
        this.future = future;
    }
}
