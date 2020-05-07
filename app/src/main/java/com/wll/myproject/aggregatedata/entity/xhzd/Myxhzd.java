package com.wll.myproject.aggregatedata.entity.xhzd;
/*
    Create by WLL on 2020/4/28 DATA: 12:10
*/

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Myxhzd implements Serializable {
    public Myxhzd() {
    }

    String reason;
    XHzidian result;
    @JSONField(name = "error_code")
    String erroCode;

    public Myxhzd(String reason, XHzidian result, String erroCode) {
        this.reason = reason;
        this.result = result;
        this.erroCode = erroCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public XHzidian getResult() {
        return result;
    }

    public void setResult(XHzidian result) {
        this.result = result;
    }

    public String getErroCode() {
        return erroCode;
    }

    public void setErroCode(String erroCode) {
        this.erroCode = erroCode;
    }
}
