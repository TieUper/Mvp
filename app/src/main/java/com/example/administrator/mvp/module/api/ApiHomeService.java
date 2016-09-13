package com.example.administrator.mvp.module.api;

import com.example.administrator.mvp.module.entity.BaseEntity;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by tie on 2016/9/6.
 */
public interface ApiHomeService {

    @Multipart
    @POST("/name")
    Observable<BaseEntity> get(@Part("name") String name,@Part("uuid") String uuid);
}
