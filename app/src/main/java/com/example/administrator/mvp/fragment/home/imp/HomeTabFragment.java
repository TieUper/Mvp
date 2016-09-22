package com.example.administrator.mvp.fragment.home.imp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.fragment.home.IHomeTabFragment;
import com.victor.loading.rotate.RotateLoading;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tie on 2016/9/12.
 */
public class HomeTabFragment extends BaseFragment implements IHomeTabFragment {


    @Bind(R.id.view_loading)
    RotateLoading mViewLoading;
    @Bind(R.id.rv_daily_list)
    RecyclerView mRvDailyList;
    @Bind(R.id.fab_calender)
    FloatingActionButton mFabCalender;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @Override
    public void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initUI() {
        mViewLoading.start();
    }

    @Override
    public void fetchData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
