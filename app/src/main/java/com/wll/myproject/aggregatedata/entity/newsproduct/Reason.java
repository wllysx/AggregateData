package com.wll.myproject.aggregatedata.entity.newsproduct;
/*
    Create by WLL on 2020/4/27 DATA: 17:19
*/

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Reason implements Serializable {
    String reason;
    MyResult result;
    @JSONField(name = "error_code")
    String errorCode;

    public Reason() {

    }

    public Reason(String reason, MyResult result, String errorCode) {
        this.reason = reason;
        this.result = result;
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public MyResult getResult() {
        return result;
    }

    public void setResult(MyResult result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
