package com.example.administrator.mvp.fragment.home.imp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.adapter.home.NewsAdapter;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.fragment.home.IHomeTabFragment;
import com.example.administrator.mvp.model.entity.News;
import com.example.administrator.mvp.model.entity.NewsEntity;
import com.example.administrator.mvp.presenter.fragment.imp.HomeFragmentPresenterImp;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tie on 2016/9/12.
 */
public class HomeTabFragment extends BaseFragment implements IHomeTabFragment,PullToRefreshBase.OnRefreshListener2 {

    @Bind(R.id.listView)
    PullToRefreshListView mListView;

    @Inject
    HomeFragmentPresenterImp mPresenterImp;
    private long mId;

    private List<News> mNewsList = new ArrayList<>();
    private NewsAdapter mAdapter;

    @Override
    public void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
        mPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {
        mListView.setOnRefreshListener(this);

        mId = getArguments().getLong("id");

        mAdapter = new NewsAdapter(getActivity(),mNewsList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void fetchData() {
        //访问网络
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPresenterImp.getNews(String.valueOf(mId));
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }

    @Override
    public void showNews(NewsEntity newsEntity) {
        mNewsList.clear();
        mNewsList.addAll(newsEntity.list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshComplete() {
        mListView.onRefreshComplete();
    }

    @Override
    public void showError() {
        mListView.onRefreshComplete();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
