package com.example.administrator.mvp.fragment.home.imp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.adapter.home.NewsAdapter;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.common.widget.refresh.XListView;
import com.example.administrator.mvp.fragment.home.IHomeTabFragment;
import com.example.administrator.mvp.model.entity.NewsEntity;
import com.example.administrator.mvp.model.greendao.News;
import com.example.administrator.mvp.presenter.fragment.imp.HomeFragmentPresenterImp;
import com.example.administrator.mvp.ui.detail.imp.NewsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tie on 2016/9/12.
 */
public class HomeTabFragment extends BaseFragment implements IHomeTabFragment, XListView.IXListViewListener, AdapterView.OnItemClickListener {

    @Bind(R.id.list_view)
    XListView mListView;

    @Inject
    HomeFragmentPresenterImp mPresenterImp;
    private long mId;

    private List<News> mNewsList = new ArrayList<>();
    private NewsAdapter mAdapter;

    private int index = 1;

    @Override
    public void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
        mPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {
        //mListView.setOnRefreshListener(this);

        refreshListener();

        mId = getArguments().getLong("id");

        mAdapter = new NewsAdapter(getActivity(),mNewsList);
        mListView.setAdapter(mAdapter);

        mPresenterImp.getNewFromDB(mId);

        mListView.setOnItemClickListener(this);
    }

    private void refreshListener() {
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(false);
        mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    @Override
    public void fetchData() {
        //访问网络
        mListView.autoRefresh();
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
    public void showNews(NewsEntity newsEntity) {
        mNewsList.clear();
        mNewsList.addAll(newsEntity.list);
        mAdapter.notifyDataSetChanged();
        if (mNewsList.size() == 20) {
            mListView.setPullLoadEnable(true);
        }

    }

    @Override
    public void refreshComplete() {
        mListView.stopRefresh();
    }

    @Override
    public void showError() {
        mListView.stopRefresh();
    }

    @Override
    public void showMore(NewsEntity newsEntity) {
        mNewsList.addAll(newsEntity.list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void useNightMode(boolean isNight) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onRefresh() {
        mPresenterImp.getNews(String.valueOf(mId));
    }

    @Override
    public void onLoadMore() {
        index++;
        mPresenterImp.getMoreNews(String.valueOf(mId),index);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        News item = mAdapter.getItem(i - 1);
        startActivity(new Intent(getActivity(), NewsDetailActivity.class).putExtra("newsId",item.getNewsID()));
    }
}
