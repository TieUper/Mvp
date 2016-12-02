package com.example.administrator.mvp.ui.detail;

import com.example.administrator.mvp.model.entity.NewsDetail;

/**
 *
 * Created by tie on 2016/12/2.
 */

public interface INewsDetailView {

    /**
     * 显示具体的新闻详情
     * @param news
     */
    void showNewsDetail(NewsDetail news);
}
