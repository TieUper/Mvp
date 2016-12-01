package com.example.administrator.mvp.model.entity;

import java.io.Serializable;

public class Preview implements Serializable {

    private int Type;

    private String Body;

    private String Body1;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getBody1() {
        return Body1;
    }

    public void setBody1(String body1) {
        Body1 = body1;
    }

    public String getBody2() {
        return Body2;
    }

    public void setBody2(String body2) {
        Body2 = body2;
    }

    private String Body2;
}