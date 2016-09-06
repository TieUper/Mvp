package com.example.administrator.mvp.common.base;

import android.support.multidex.MultiDexApplication;

import com.example.administrator.mvp.common.injector.component.AppComponent;
import com.example.administrator.mvp.common.injector.component.DaggerAppComponent;
import com.example.administrator.mvp.common.injector.module.ApiModule;
import com.example.administrator.mvp.common.injector.module.AppModule;


/**
 * Created by Administrator on 2016/9/6.
 */
public class MyApplication extends MultiDexApplication {

    private AppComponent mAppComponent;

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //dagger2
        initDagger2();
    }

    private void initDagger2() {
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getClientAppComponent() {
        return mAppComponent;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
