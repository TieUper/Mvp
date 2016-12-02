package com.example.administrator.mvp.model.api;

import com.example.administrator.mvp.model.entity.CategoryEntity;
import com.example.administrator.mvp.model.entity.NewsDetail;
import com.example.administrator.mvp.model.entity.NewsEntity;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tie on 2016/9/6.
 */
public interface ApiHomeService {

//    String Host = "http://innosharevm.chinacloudapp.cn:80/innoshare/mobilephone/users/";

    String Host = "http://api.tongmedia.com.hk";

    /**
     * @return
     */
    @POST("/Api/AppRequest/Category")
    Observable<CategoryEntity> getCategory(@Body Map<String,String> param);

    /**
     * 获取新闻
     */
    @POST("/Api/AppRequest/News")
    Observable<NewsEntity> getNews(@Body Map<String,String> param);

    /**
     * 获取新闻详情
     */
    @POST("/api/AppRequest/GetNewsDetail")
    Observable<NewsDetail> getNewsDetail(@Body Map<String,String> param);
}
