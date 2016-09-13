package com.example.administrator.mvp.presenter;

import com.example.administrator.mvp.ui.IView;

/**
 * Created by tie on 2016/9/13.
 */
public interface IPresenter {
    /**
     * 注入View，与presenter 响应
     */
    void attachView(IView iView);

    /**
     * 释放资源
     */
    void detachView();
}
