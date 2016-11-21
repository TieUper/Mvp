package com.example.administrator.mvp.presenter.splash.imp;

import com.example.administrator.mvp.common.utils.RxUtil;
import com.example.administrator.mvp.model.api.ApiZhihuService;
import com.example.administrator.mvp.presenter.splash.SplashPresenter;
import com.example.administrator.mvp.ui.IView;
import com.example.administrator.mvp.ui.splash.ISplashActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by tie on 2016/9/13.
 */
public class SplashPresenterImp implements SplashPresenter {

    private RxAppCompatActivity mActivity;

    private ISplashActivity mISplashActivity;

    private static final String RES = "1080*1776";

    private static final int COUNT_DOWN_TIME = 2200;

    @Inject
    ApiZhihuService mApiZhihuService;

    @Inject
    public SplashPresenterImp(RxAppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(IView iView) {
        mISplashActivity = (ISplashActivity) iView;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getSplashData() {
        mApiZhihuService.getWelcomeInfo(RES)
                .compose(RxUtil.rxSchedulerHelper(mActivity))
                .subscribe(welcomeBean -> {
                    mISplashActivity.showContent(welcomeBean);
                    startCountDown();
                }, throwable -> {
//                        mView.showError("");
                    mISplashActivity.jumpToMain();
                });
    }

    /**
     * 延时跳转
     */
    private void startCountDown() {
        Observable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper(mActivity))
                .subscribe(aLong -> {
                    mISplashActivity.jumpToMain();
                });

    }
}
