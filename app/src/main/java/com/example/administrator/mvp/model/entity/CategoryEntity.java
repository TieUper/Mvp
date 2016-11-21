package com.example.administrator.mvp.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * 频道
 * Created by wutong on 2016/3/3.
 */
public class CategoryEntity extends BaseEntity {
    @SerializedName(value = "Content")
    public ArrayList<Category> list;
}
