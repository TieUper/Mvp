package com.example.administrator.mvp.model.entity;

import com.example.administrator.mvp.model.greendao.News;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 收藏页面新闻
 * Created by Administrator on 2016/3/7 0007.
 */
public class NewsEntity extends BaseEntity {
    @SerializedName(value = "Content")
    public List<News> list;

    @SerializedName(value = "NewsCount")
    public int newsCount;


}
