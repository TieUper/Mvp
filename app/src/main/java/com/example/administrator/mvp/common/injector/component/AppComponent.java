package com.example.administrator.mvp.common.injector.component;

import com.example.administrator.mvp.common.base.MyApplication;
import com.example.administrator.mvp.common.injector.module.ApiModule;
import com.example.administrator.mvp.common.injector.module.AppModule;
import com.example.administrator.mvp.data.api.ApiHomeService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tie on 2016/9/6.
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    void inject(MyApplication myApplication);

    ApiHomeService getApiHomeService();
}