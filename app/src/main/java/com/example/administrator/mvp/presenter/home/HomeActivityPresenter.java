package com.example.administrator.mvp.presenter.home;

import com.example.administrator.mvp.presenter.IPresenter;

/**
 * Created by tie on 2016/9/13.
 */
public interface HomeActivityPresenter extends IPresenter {

    /**
     * 获取新闻详情
     * @param newsId
     */
    void getNewsDetail(String newsId);

}
