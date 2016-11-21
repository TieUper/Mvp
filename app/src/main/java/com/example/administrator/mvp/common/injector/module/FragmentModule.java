package com.example.administrator.mvp.common.injector.module;

import android.content.Context;

import com.example.administrator.mvp.common.injector.component.ContextLife;
import com.trello.rxlifecycle.components.support.RxFragment;

import dagger.Module;
import dagger.Provides;

import static android.R.attr.fragment;

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
    @ContextLife(value = "Activity")
    Context provideContext() {
        return mRxFragment.getActivity();
    }

    @Provides
    @PerFragment
    RxFragment provideRxFragment() {
        return mRxFragment;
    }
}
