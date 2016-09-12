package com.example.administrator.mvp.ui.home.imp;

import android.support.v4.view.ViewPager;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.fragment.BaseFragment;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends BaseActivity implements IMainActivity, ViewPager.OnPageChangeListener {


    private SmartTabLayout viewpagerTab;
    private ViewPager viewPager;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initUI() {
        viewpagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        for (int i = 0; i < 10; i++) {

            creator.add("测试", BaseFragment.class);
        }
        //得到一个集合
        FragmentPagerItems pagerItems = creator.create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pagerItems);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter);
        viewpagerTab.setOnPageChangeListener(this);

        //viewpagerTab与viewPager连用
        viewpagerTab.setViewPager(viewPager);
        viewpagerTab.setOnTabClickListener(new SmartTabLayout.OnTabClickListener() {
            @Override
            public void onTabClicked(int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }


    @Override
    public void register() {

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
