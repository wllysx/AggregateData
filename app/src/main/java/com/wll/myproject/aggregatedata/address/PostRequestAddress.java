package com.wll.myproject.aggregatedata.address;
/*
    Create by WLL on 2020/4/27 DATA: 11:30
*/

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PostRequestAddress extends BaseAddress{

    //验证码登录。11
    public static String loginCode() {
        return HOST + "";
    }

    //密码登录。
    public static String loginPassword() {
        return HOST + "";
    }

    //成语词典post请求。
    public static String postChengYu(String query,String dType) throws UnsupportedEncodingException {
        return getHost() + "/chengyu/query" + "?word=" + URLDecoder.decode( query, "UTF-8" ) + "&dtyle=" + dType + "&key=325d311cc4048fe47102e360c3178d56";

    }
}
