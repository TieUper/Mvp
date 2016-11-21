package com.example.administrator.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * APP最原始的实体类，大多数都是继承于它
 * Created by wutong on 2016/3/3.
 */
public class BaseEntity implements Serializable {
    @SerializedName(value = "Result")
    public int result;
    @SerializedName(value = "Resion")
    public String reason;
    @SerializedName(value = "Exist")
    public int exist;
    @SerializedName(value = "Status")
    public int status;

    @SerializedName(value = "RejectReasons")
    public String rejectReason;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getExist() {
        return exist;
    }

    public void setExist(int exist) {
        this.exist = exist;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "result=" + result +
                ", reason='" + reason + '\'' +
                ", exist=" + exist +
                ", status=" + status +
                '}';
    }
}
