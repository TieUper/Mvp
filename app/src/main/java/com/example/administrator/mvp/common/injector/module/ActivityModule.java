package com.example.administrator.mvp.common.injector.module;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tie on 2016/9/6.
 */
@Module
public class ActivityModule {

    private RxAppCompatActivity mActivity;

    public ActivityModule(RxAppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    RxAppCompatActivity provideRxAppCompatActivity() {
        return mActivity;
    }
}
