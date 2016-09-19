package com.example.administrator.mvp.ui.splash;

import com.example.administrator.mvp.model.entity.WelcomeBean;
import com.example.administrator.mvp.ui.IView;

/**
 * Created by tie on 2016/9/13.
 */
public interface ISplashActivity extends IView {

    /**
     * Splash显示内容
     * @param welcomeBean
     */
    void showContent(WelcomeBean welcomeBean);

    //跳转到主页
    void jumpToMain();
}
