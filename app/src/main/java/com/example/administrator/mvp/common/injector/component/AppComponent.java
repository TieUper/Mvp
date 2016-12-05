package com.example.administrator.mvp.common.injector.component;

import com.example.administrator.mvp.common.base.MyApplication;
import com.example.administrator.mvp.common.injector.module.ApiModule;
import com.example.administrator.mvp.common.injector.module.AppModule;
import com.example.administrator.mvp.common.utils.DbUtils;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.common.utils.PackageUtils;
import com.example.administrator.mvp.common.widget.refresh.MyRefreshUtils;
import com.example.administrator.mvp.model.api.ApiHomeService;
import com.example.administrator.mvp.model.api.ApiZhihuService;
import com.example.administrator.mvp.model.entity.RequestParam;

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

    //图片工具类
    ImageLoader getImageLoader();

    ApiZhihuService getApiZhihuService();

    //参数工具类
    RequestParam getRequestParams();

    MyRefreshUtils getRefreshUtils();

    //数据库工具类
    DbUtils getDbUtils();

    //包管理工具
    PackageUtils getPackageUtils();
}
