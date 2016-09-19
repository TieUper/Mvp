package com.example.administrator.mvp.common.injector.module;

import com.example.administrator.mvp.common.interceptor.RequestParamInterceptor;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.api.ApiHomeService;
import com.example.administrator.mvp.model.api.ApiZhihuService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tie on 2016/9/6.
 */
@Module
public class ApiModule {

    private final Retrofit zhihuRetrofit;

    private final Retrofit homeRetrofit;

    public ApiModule() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        RequestParamInterceptor requestParamInterceptor = new RequestParamInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(requestParamInterceptor)
                .build();

        //知乎Retrofit初始化
        zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(ApiZhihuService.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        homeRetrofit = new Retrofit.Builder()
                .baseUrl(ApiHomeService.Host)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    @Provides
    @Singleton
    ApiHomeService provideHomeService() {
        return homeRetrofit.create(ApiHomeService.class);
    }

    @Provides
    @Singleton
    ApiZhihuService provideZhihuService() {
        return zhihuRetrofit.create(ApiZhihuService.class);
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader() {
        return new ImageLoader();
    }

}
