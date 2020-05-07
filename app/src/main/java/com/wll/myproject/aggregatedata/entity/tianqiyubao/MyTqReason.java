package com.wll.myproject.aggregatedata.entity.tianqiyubao;
/*
    Create by WLL on 2020/4/28 DATA: 15:43
*/

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class MyTqReason implements Serializable {
    public MyTqReason() {
    }

    String reason;
    MyTqResult result;
    @JSONField(name = "error_code")
    String errorCode;

    public MyTqReason(String reason, MyTqResult result, String errorCode) {
        this.reason = reason;
        this.result = result;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "MyTqReason{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public MyTqResult getResult() {
        return result;
    }

    public void setResult(MyTqResult result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
