package com.example.administrator.mvp.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mvp.common.injector.component.DaggerFragmentComponent;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.common.injector.module.FragmentModule;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by tie on 2016/9/9.
 */
public abstract class BaseFragment extends RxFragment {

    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDagger2();
    }

    private void initDagger2() {
        FragmentComponent build = DaggerFragmentComponent.builder()
                .appComponent(MyApplication.getInstance().getClientAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();

        inject(build);
    }

    public abstract void inject(FragmentComponent fragmentComponent);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = initRootView(inflater, container, savedInstanceState);
            initUI();
        } else {
            ViewGroup viewGroup = ((ViewGroup) mRootView.getParent());
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }

        }

//        ifNeedLoadData();

        return mRootView;
    }

    protected abstract void initUI();

    protected abstract View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
}
