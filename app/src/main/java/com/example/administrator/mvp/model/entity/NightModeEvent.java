package com.example.administrator.mvp.model.entity;

/**
 * 夜间模式传递事件
 * Created by tie on 2016/11/23.
 */
public class NightModeEvent {

    private boolean isNightMode;

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }
}
