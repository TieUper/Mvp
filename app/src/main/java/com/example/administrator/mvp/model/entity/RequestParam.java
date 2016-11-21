package com.example.administrator.mvp.model.entity;

import android.content.Context;

import com.example.administrator.mvp.common.injector.component.ContextLife;
import com.example.administrator.mvp.common.utils.UUIDUtil;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by tie on 2016/11/18.
 */

public class RequestParam {

    private Context mContext;
    private Map<String,String> mMap = new HashMap();


    @Inject
    public RequestParam(@ContextLife("Application") Context context) {
        this.mContext = context.getApplicationContext();
        init();
    }

    private void init() {
        mMap.put("UUID", UUIDUtil.getUniqueID(mContext));
        mMap.put("LanguageType","zh-Hans");
    }

    public Map<String,String> getParams() {
        return mMap;
    }

    public RequestParam addParam(String key,String value){
        mMap.put(key,value);
        return RequestParam.this;
    }
}
