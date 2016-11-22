package com.example.administrator.mvp.common.widget.refresh;

import android.content.Context;

import com.example.administrator.mvp.common.injector.component.ContextLife;

import javax.inject.Inject;

/**
 * Created by tie on 2016/11/22.
 */

public class MyRefreshUtils {

    private Context mContext;

    @Inject
    public MyRefreshUtils(@ContextLife("Application") Context context) {
        this.mContext = context.getApplicationContext();
        init();
    }

    private void init() {

    }
}
