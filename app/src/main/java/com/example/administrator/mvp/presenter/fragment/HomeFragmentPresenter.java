package com.example.administrator.mvp.presenter.fragment;

import com.example.administrator.mvp.presenter.IPresenter;

/**
 * Created by tie on 2016/11/21.
 */

public interface HomeFragmentPresenter  extends IPresenter{
    //获取新闻
    void getNews(String id);
    //获取更多新闻
    void getMoreNews(String id,int index);
    //获取目录
    void getCategory();
}
