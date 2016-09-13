package com.example.administrator.mvp.ui.home.imp;

import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.fragment.home.imp.HomeTabFragment;
import com.example.administrator.mvp.presenter.home.imp.HomeActivityPresenterImp;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements IMainActivity, ViewPager.OnPageChangeListener {


    @Bind(R.id.viewpagerTab)
    SmartTabLayout mViewpagerTab;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;

    @Inject
    HomeActivityPresenterImp mHomeActivityPresenterImp;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        ButterKnife.bind(this);
        activityComponent.inject(this);
        mHomeActivityPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {
        mHomeActivityPresenterImp.give();
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        for (int i = 0; i < 10; i++) {

            creator.add("测试", HomeTabFragment.class);
        }
        //得到一个集合
        FragmentPagerItems pagerItems = creator.create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pagerItems);
        mViewpager.setAdapter(adapter);
        mViewpagerTab.setOnPageChangeListener(this);

        //viewpagerTab与viewPager连用
        mViewpagerTab.setViewPager(mViewpager);
        mViewpagerTab.setOnTabClickListener(new SmartTabLayout.OnTabClickListener() {
            @Override
            public void onTabClicked(int position) {
                mViewpager.setCurrentItem(position);
            }
        });
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }


    @Override
    public void register(String game) {
        Toast.makeText(this,game,Toast.LENGTH_LONG).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
