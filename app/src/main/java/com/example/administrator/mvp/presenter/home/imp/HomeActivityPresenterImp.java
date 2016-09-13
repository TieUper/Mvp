package com.example.administrator.mvp.presenter.home.imp;

import android.content.Context;

import com.example.administrator.mvp.common.injector.module.PerActivity;
import com.example.administrator.mvp.presenter.home.HomeActivityPresenter;
import com.example.administrator.mvp.ui.IView;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * Created by tie on 2016/9/13.
 */
@PerActivity
public class HomeActivityPresenterImp implements HomeActivityPresenter{

    private IMainActivity mIMainActivity;

    private RxAppCompatActivity mActivity;

    private Context mContext;

//    @Inject
//    public HomeActivityPresenterImp(@ContextLife(value = "Activity") Context context, RxAppCompatActivity activity) {
//        mActivity = activity;
//        mContext = context;
//    }

    @Inject
    public HomeActivityPresenterImp(RxAppCompatActivity activity) {
        mActivity = activity;
    }

    @Override
    public void attachView(IView iView) {
        mIMainActivity  = (IMainActivity) iView;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void give() {
        mIMainActivity.register("我注册了");
    }
}
