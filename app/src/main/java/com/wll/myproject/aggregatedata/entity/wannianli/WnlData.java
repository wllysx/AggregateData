package com.wll.myproject.aggregatedata.entity.wannianli;
/*
    Create by WLL on 2020/4/29 DATA: 9:56
*/

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class WnlData implements Serializable {
    String holiday;
    String avoid;
    String animalsYear;
    String desc;
    String weekday;
    String suit;
    String lunarYear;
    String lunar;
    @JSONField(name = "year-month")
    String yearMonth;
    String date;

    public WnlData() {
    }

    public WnlData(String holiday, String avoid, String animalsYear, String desc, String weekday, String suit, String lunarYear, String lunar, String yearMonth, String date) {
        this.holiday = holiday;
        this.avoid = avoid;
        this.animalsYear = animalsYear;
        this.desc = desc;
        this.weekday = weekday;
        this.suit = suit;
        this.lunarYear = lunarYear;
        this.lunar = lunar;
        this.yearMonth = yearMonth;
        this.date = date;
    }

    @Override
    public String toString() {
        return "WnlData{" +
                "holiday='" + holiday + '\'' +
                ", avoid='" + avoid + '\'' +
                ", animalsYear='" + animalsYear + '\'' +
                ", desc='" + desc + '\'' +
                ", weekday='" + weekday + '\'' +
                ", suit='" + suit + '\'' +
                ", lunarYear='" + lunarYear + '\'' +
                ", lunar='" + lunar + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getAnimalsYear() {
        return animalsYear;
    }

    public void setAnimalsYear(String animalsYear) {
        this.animalsYear = animalsYear;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
