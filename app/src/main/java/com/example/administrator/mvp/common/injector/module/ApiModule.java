package com.example.administrator.mvp.common.injector.module;

import com.example.administrator.mvp.data.api.ApiHomeService;

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

    /**
     * 根地址
     */
    private static final String ENDPOINT = "";

    private final Retrofit retrofit;

    public ApiModule() {
        //日志拦截器 打印网络请求日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
//                .addNetworkInterceptor(requestParamInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiHomeService provideHomeService() {
        return retrofit.create(ApiHomeService.class);
    }

}
