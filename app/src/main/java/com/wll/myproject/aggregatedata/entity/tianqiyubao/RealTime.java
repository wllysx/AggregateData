package com.wll.myproject.aggregatedata.entity.tianqiyubao;
/*
    Create by WLL on 2020/4/28 DATA: 15:47
*/

import java.io.Serializable;

public class RealTime implements Serializable {
    String temperature;
    String humidity;
    String info;
    String wid;
    String direct;
    String power;
    String aqi;
    public RealTime() {
    }

    public RealTime(String temperature, String humidity, String info, String wid, String direct, String power, String aqi) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.info = info;
        this.wid = wid;
        this.direct = direct;
        this.power = power;
        this.aqi = aqi;
    }

    @Override
    public String toString() {
        return "RealTime{" +
                "temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                ", info='" + info + '\'' +
                ", wid='" + wid + '\'' +
                ", direct='" + direct + '\'' +
                ", power='" + power + '\'' +
                ", aqi='" + aqi + '\'' +
                '}';
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }
}
