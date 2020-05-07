package com.wll.myproject.aggregatedata.address;
/*
    Create by WLL on 2020/4/27 DATA: 11:26
*/

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class GetRequestAddress extends BaseAddress {

    //获得相应分类的新闻信息。
    public static String getNews(String title) {
        return getHost()+"/toutiao/index" + "?type=" + title + "&key=4ec067a89eb982a971ade460f534fa9a";
    }

    public static String getxhzd(String query) throws UnsupportedEncodingException {
        //将汉字转化为utf-8
        return getHost() + "/xhzd/query" + "?word=" + URLDecoder.decode( query, "UTF-8" )+"&dtype=&key=b03654404a48a320958bec6a16d60eeb";
    }

    public static String getTqyb(String query) throws UnsupportedEncodingException {
        return getHost1() + "/simpleWeather/query" + "?city=" + URLDecoder.decode( query, "UTF-8" )+"&key=18b12c622bd49296f75a90f81704bda9";
    }

    //获取当前选择的日期，来获得此日期的详情。
    public static String getDateTime(String dataTime) {
        return getHost() + "/calendar/day" + "?date=" + dataTime + "&key=5909188b39ecc748446cc56ab1da30e9";
    }
}
