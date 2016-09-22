package com.example.administrator.mvp.fragment.home.imp;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.fragment.home.IRefreshFragment;

/**
 * Created by tie on 2016/9/21.
 */

public class RefreshFrgment extends BaseFragment implements IRefreshFragment {
    @Override
    public void inject(FragmentComponent fragmentComponent) {

    }

    @Override
    protected void initUI() {

    }

    @Override
    public void fetchData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_refresh;
    }
}
