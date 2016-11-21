package com.example.administrator.mvp.model.entity;


import java.io.Serializable;

public class Category implements Serializable{

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Integer getTypeID() {
        return TypeID;
    }

    public void setTypeID(Integer typeID) {
        TypeID = typeID;
    }

    public Integer getIndex() {
        return Index;
    }

    public void setIndex(Integer index) {
        Index = index;
    }

    public String getCategory_Traditional() {
        return Category_Traditional;
    }

    public void setCategory_Traditional(String category_Traditional) {
        Category_Traditional = category_Traditional;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getAttention() {
        return Attention;
    }

    public void setAttention(int attention) {
        Attention = attention;
    }

    public long ID;

    public int Attention;

    public String Category;

    public String Category_Traditional;

    public Integer Index;

    public Integer TypeID;
}
