package com.example.administrator.mvp.ui.splash.imp;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.presenter.splash.imp.SplashPresenterImp;
import com.example.administrator.mvp.ui.splash.ISplashActivity;

import javax.inject.Inject;

/**
 * Created by tie on 2016/9/13.
 */
public class SplashActiviy extends BaseActivity implements ISplashActivity {

    @Inject
    SplashPresenterImp mSplashPresenterImp;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        mSplashPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {
        mSplashPresenterImp.getSplashData();
    }


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_splash;
    }
}
