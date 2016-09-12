package com.example.administrator.mvp.common.injector.module;

import com.trello.rxlifecycle.components.support.RxFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tie on 2016/9/12.
 */
@Module
public class FragmentModule {

    private RxFragment mRxFragment;

    public FragmentModule(RxFragment rxFragment) {
        mRxFragment = rxFragment;
    }

    @Provides
    @PerFragment
    RxFragment provideRxFragment() {
        return mRxFragment;
    }
}
