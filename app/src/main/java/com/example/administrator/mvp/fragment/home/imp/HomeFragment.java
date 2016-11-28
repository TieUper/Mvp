package com.example.administrator.mvp.fragment.home.imp;

import android.support.v4.view.ViewPager;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.fragment.home.IHomeFragment;
import com.example.administrator.mvp.model.entity.Category;
import com.example.administrator.mvp.model.entity.CategoryEntity;
import com.example.administrator.mvp.presenter.fragment.imp.HomeFragmentPresenterImp;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by tie on 2016/11/28.
 */

public class HomeFragment extends BaseFragment implements IHomeFragment, ViewPager.OnPageChangeListener {


    @Bind(R.id.viewpagerTab)
    SmartTabLayout mViewpagerTab;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    @Inject
    HomeFragmentPresenterImp mPresenter;

    @Override
    public void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initUI() {
        mPresenter.getCategory();
    }

    @Override
    public void fetchData() {

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_main_home;
    }


    @Override
    public void showCategory(CategoryEntity categoryEntity) {
        ArrayList<Category> list = categoryEntity.list;
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getActivity());
        for (int i = 0; i < list.size(); i++) {
            Category category = list.get(i);
            creator.add(category.getCategory(), HomeTabFragment.class, new Bundler().putLong("id", category.getID()).get());
        }
        //得到一个集合
        FragmentPagerItems pagerItems = creator.create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getActivity().getSupportFragmentManager(), pagerItems);
        mViewpager.setAdapter(adapter);
        mViewpagerTab.setOnPageChangeListener(this);

        //viewpagerTab与viewPager连用
        mViewpagerTab.setViewPager(mViewpager);
        mViewpagerTab.setOnTabClickListener(position -> mViewpager.setCurrentItem(position));
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
