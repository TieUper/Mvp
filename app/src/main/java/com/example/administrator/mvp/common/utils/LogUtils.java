package com.example.administrator.mvp.common.utils;

import com.socks.library.KLog;

/**
 * 日志管理工具 使用Klog
 * Created by tie on 2016/12/5.
 */

public class LogUtils {

    //Debug
    public static void d(String message){
        KLog.d(message);
    }

    //Json显示
    public static void json(String json) {
        KLog.json(json);
    }
}
