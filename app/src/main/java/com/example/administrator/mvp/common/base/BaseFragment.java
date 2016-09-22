package com.example.administrator.mvp.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mvp.common.injector.component.DaggerFragmentComponent;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.common.injector.module.FragmentModule;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by tie on 2016/9/9.
 */
public abstract class BaseFragment extends RxFragment {

    private View mRootView;
    private boolean isViewInitiated;

    private boolean isVisibleToUser;
    private boolean isDataInitiated;

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
            mRootView = inflater.inflate(getLayoutResID(),container,false);
            ButterKnife.bind(this,mRootView);
            initUI();
        } else {
            ViewGroup viewGroup = ((ViewGroup) mRootView.getParent());
            if (viewGroup != null) {
                viewGroup.removeView(mRootView);
            }
        }

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    private void prepareFetchData() {
         prepareFetchData(false);
    }

    private void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
        }
    }

    //初始化UI
    protected abstract void initUI();
    //访问网络,加载数据
    public abstract void fetchData();

    /**
     * getContentView ID
     *
     * @return  布局的id
     */
    protected abstract int getLayoutResID();
}
