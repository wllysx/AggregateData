package com.wll.myproject.aggregatedata.entity.cycd;
/*
    Create by WLL on 2020/5/6 DATA: 16:33
*/

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

public class Cycd implements Serializable {
    public Cycd( ) {
    }

    private String bushou;
    private String head;
    private String pinyin;
    private String chengyujs;

    @JSONField(name = "from_")
    private String from;
    private String example;
    private String yufa;
    private String ciyujs;
    private String yinzhengjs;
    List<String> tongyi;
    List<String> fanyi;

    public Cycd(String bushou, String head, String pinyin, String chengyujs, String from, String example, String yufa, String ciyujs, String yinzhengjs, List<String> tongyi, List<String> fanyi) {
        this.bushou = bushou;
        this.head = head;
        this.pinyin = pinyin;
        this.chengyujs = chengyujs;
        this.from = from;
        this.example = example;
        this.yufa = yufa;
        this.ciyujs = ciyujs;
        this.yinzhengjs = yinzhengjs;
        this.tongyi = tongyi;
        this.fanyi = fanyi;
    }

    @Override
    public String toString() {
        return "Cycd{" +
                "bushou='" + bushou + '\'' +
                ", head='" + head + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", chengyujs='" + chengyujs + '\'' +
                ", from='" + from + '\'' +
                ", example='" + example + '\'' +
                ", yufa='" + yufa + '\'' +
                ", ciyujs='" + ciyujs + '\'' +
                ", yinzhengjs='" + yinzhengjs + '\'' +
                ", tongyi=" + tongyi +
                ", fanyi=" + fanyi +
                '}';
    }

    public String getBushou() {
        return bushou;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getChengyujs() {
        return chengyujs;
    }

    public void setChengyujs(String chengyujs) {
        this.chengyujs = chengyujs;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getYufa() {
        return yufa;
    }

    public void setYufa(String yufa) {
        this.yufa = yufa;
    }

    public String getCiyujs() {
        return ciyujs;
    }

    public void setCiyujs(String ciyujs) {
        this.ciyujs = ciyujs;
    }

    public String getYinzhengjs() {
        return yinzhengjs;
    }

    public void setYinzhengjs(String yinzhengjs) {
        this.yinzhengjs = yinzhengjs;
    }

    public List<String> getTongyi() {
        return tongyi;
    }

    public void setTongyi(List<String> tongyi) {
        this.tongyi = tongyi;
    }

    public List<String> getFanyi() {
        return fanyi;
    }

    public void setFanyi(List<String> fanyi) {
        this.fanyi = fanyi;
    }
}
