package com.wll.myproject.aggregatedata.entity.xhzd;
/*
    Create by WLL on 2020/4/28 DATA: 11:39
*/

import java.io.Serializable;
import java.util.List;

public class XHzidian implements Serializable {
    public XHzidian() {
    }

    String id;
    String zi;
    String py;
    String wubi;
    String pingyin;
    String bushou;
    String bihua;
    List<String> jijie;//基本解释
    List<String> xiangjie;//详细解释。

    public XHzidian(String id, String zi, String py, String wubi, String pingyin, String bushou, String bihua, List<String> jijie, List<String> xiangjie) {
        this.id = id;
        this.zi = zi;
        this.py = py;
        this.wubi = wubi;
        this.pingyin = pingyin;
        this.bushou = bushou;
        this.bihua = bihua;
        this.jijie = jijie;
        this.xiangjie = xiangjie;
    }

    @Override
    public String toString() {
        return "XHzidian{" +
                "id='" + id + '\'' +
                ", zi='" + zi + '\'' +
                ", py='" + py + '\'' +
                ", wubi='" + wubi + '\'' +
                ", pingyin='" + pingyin + '\'' +
                ", bushou='" + bushou + '\'' +
                ", bihua='" + bihua + '\'' +
                ", jijie=" + jijie +
                ", xiangjie=" + xiangjie +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getWubi() {
        return wubi;
    }

    public void setWubi(String wubi) {
        this.wubi = wubi;
    }

    public String getPingyin() {
        return pingyin;
    }

    public void setPingyin(String pingyin) {
        this.pingyin = pingyin;
    }

    public String getBushou() {
        return bushou;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    public String getBihua() {
        return bihua;
    }

    public void setBihua(String bihua) {
        this.bihua = bihua;
    }

    public List<String> getJijie() {
        return jijie;
    }

    public void setJijie(List<String> jijie) {
        this.jijie = jijie;
    }

    public List<String> getXiangjie() {
        return xiangjie;
    }

    public void setXiangjie(List<String> xiangjie) {
        this.xiangjie = xiangjie;
    }
}
