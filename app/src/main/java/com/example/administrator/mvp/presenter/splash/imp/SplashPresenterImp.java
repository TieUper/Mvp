package com.example.administrator.mvp.presenter.splash.imp;

import com.example.administrator.mvp.module.api.ApiHomeService;
import com.example.administrator.mvp.presenter.splash.SplashPresenter;
import com.example.administrator.mvp.ui.IView;
import com.example.administrator.mvp.ui.splash.ISplashActivity;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tie on 2016/9/13.
 */
public class SplashPresenterImp implements SplashPresenter {

    private RxAppCompatActivity mActivity;

    private ISplashActivity mISplashActivity;

    @Inject
    ApiHomeService mApiHomeService;

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
        mApiHomeService.get("zhangsan","abcdefghijklmn")
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }
}
