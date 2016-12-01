package com.example.administrator.mvp.fragment.home.imp;

import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseFragment;
import com.example.administrator.mvp.common.injector.component.FragmentComponent;
import com.example.administrator.mvp.fragment.home.IHomeFragment;
import com.example.administrator.mvp.model.greendao.Category;
import com.example.administrator.mvp.presenter.fragment.imp.HomeFragmentPresenterImp;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

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

    private ArrayList<Category> mCategories = new ArrayList<>();

    @Override
    public void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initUI() {
        //数据库加载
        mPresenter.loadCategoryFromDb();
        //网络加载
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
    public void showCategory(List<Category> list) {
        //空 不加载
        if(list.size() == 0) {
            return;
        }
        //数据库数据
        if( mCategories.size() == list.size() && mCategories.containsAll(list) ){
            return;
        }
        mCategories.addAll(list);
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getActivity());
        for (int i = 0; i < mCategories.size(); i++) {
            Category category = mCategories.get(i);
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

    //夜间模式
    @Override
    public void useNightMode(boolean isNight) {
        TypedValue background = new TypedValue();//背景色
        Resources.Theme theme = getActivity().getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        mViewpagerTab.setBackgroundResource(background.resourceId);
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
