package com.example.administrator.mvp.data.api;

import com.example.administrator.mvp.data.entity.BaseEntity;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by tie on 2016/9/6.
 */
public interface ApiHomeService {


    @GET
    Observable<BaseEntity> get();
}
