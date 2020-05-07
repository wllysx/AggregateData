package com.wll.myproject.aggregatedata.entity.tianqiyubao;
/*
    Create by WLL on 2020/4/28 DATA: 15:48
*/

import java.io.Serializable;

public class FutureTQ implements Serializable {
    String date;
    String temperature;
    String weather;
    String direct;
    public FutureTQ() {
    }

    public FutureTQ(String date, String temperature, String weather, String direct) {
        this.date = date;
        this.temperature = temperature;
        this.weather = weather;
        this.direct = direct;
    }

    @Override
    public String toString() {
        return "FutureTQ{" +
                "date='" + date + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", direct='" + direct + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
}
