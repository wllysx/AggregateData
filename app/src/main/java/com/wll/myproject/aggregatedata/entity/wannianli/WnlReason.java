package com.wll.myproject.aggregatedata.entity.wannianli;
/*
    Create by WLL on 2020/4/29 DATA: 9:54
*/

import java.io.Serializable;

public class WnlReason implements Serializable {
    String reason;
    MyWnlResult result;
    String error_code;
    public WnlReason() {
    }

    public WnlReason(String reason, MyWnlResult result, String error_code) {
        this.reason = reason;
        this.result = result;
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "WnlReason{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code='" + error_code + '\'' +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public MyWnlResult getResult() {
        return result;
    }

    public void setResult(MyWnlResult result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
