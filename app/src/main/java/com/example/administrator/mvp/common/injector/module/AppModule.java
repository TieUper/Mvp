package com.example.administrator.mvp.common.injector.module;

import com.example.administrator.mvp.common.base.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tie on 2016/9/6.
 */
@Module
public class AppModule {

    private MyApplication mMyApplication;

    public AppModule(MyApplication myApplication) {
        mMyApplication = myApplication;
    }

    @Provides
    @Singleton
    MyApplication provideMyApplication() {
        return mMyApplication;
    }
}