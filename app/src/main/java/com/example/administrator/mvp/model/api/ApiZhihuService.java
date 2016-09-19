package com.example.administrator.mvp.model.api;

import com.example.administrator.mvp.model.entity.WelcomeBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tie on 2016/9/19.
 */
public interface ApiZhihuService {

    String HOST = "http://news-at.zhihu.com/api/4/";

    /**
     * 启动界面图片
     */
    @GET("start-image/{res}")
    Observable<WelcomeBean> getWelcomeInfo(@Path("res") String res);
}
