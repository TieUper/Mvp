package com.example.administrator.mvp.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.administrator.mvp.common.base.MyApplication;

import javax.inject.Inject;

/**
 * Created by tie on 2016/12/5.
 */

public class PackageUtils {
    private Context mContext;

    @Inject
    public PackageUtils(MyApplication myApplication){
        mContext = myApplication;
    }

    /**
     2  * 获取版本号
     3  * @return 当前应用的版本号
     4  */
     public  String getVersion() {
             try {
                     PackageManager manager = mContext.getPackageManager();
                     PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
                     String version = info.versionName;
                     return version;
                 } catch (Exception e) {
                     e.printStackTrace();
                     return e.getMessage();
                }
         }
}
