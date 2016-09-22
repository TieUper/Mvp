package com.example.administrator.mvp.ui.home.imp;

import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.fragment.home.imp.HomeTabFragment;
import com.example.administrator.mvp.fragment.home.imp.RefreshFrgment;
import com.example.administrator.mvp.presenter.home.imp.HomeActivityPresenterImp;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
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

    @Bind(R.id.drawer)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.view_search)
    MaterialSearchView mViewSearch;
    @Bind(R.id.navigation)
    NavigationView mNavigation;

    private ActionBarDrawerToggle mDrawerToggle;
    private MenuItem mLastItem;

    private MenuItem mSearchMenuItem;

    @Override
    protected void onBackPressedSupport() {
        finish();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        ButterKnife.bind(this);
        activityComponent.inject(this);
        mHomeActivityPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {
        //初始化Toolbar和Drawer
        init();
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(this);
        for (int i = 0; i < 10; i++) {

            creator.add("测试", HomeTabFragment.class);
        }
        for (int i = 0; i < 2; i++) {
            creator.add("首页", RefreshFrgment.class);
        }
        //得到一个集合
        FragmentPagerItems pagerItems = creator.create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pagerItems);
        mViewpager.setAdapter(adapter);
        mViewpagerTab.setOnPageChangeListener(this);

        //viewpagerTab与viewPager连用
        mViewpagerTab.setViewPager(mViewpager);
        mViewpagerTab.setOnTabClickListener(position -> mViewpager.setCurrentItem(position));
    }

    private void init() {
        setToolBar(mToolBar, "主页");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLastItem = mNavigation.getMenu().findItem(R.id.drawer_zhihu);
        mNavigation.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.drawer_zhihu:
                    break;
                case R.id.drawer_gank:
                    break;
                case R.id.drawer_wechat:
                    break;
                case R.id.drawer_setting:
                    break;
                case R.id.drawer_like:
                    break;
                case R.id.drawer_about:
                    break;
            }

            if(mLastItem != null) {
                mLastItem.setChecked(false);
            }
            mLastItem = item;

            item.setChecked(true);
            mToolBar.setTitle(item.getTitle());
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mViewSearch.setMenuItem(item);
        mSearchMenuItem = item;
        return true;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }


    @Override
    public void register(String game) {
        Toast.makeText(this, game, Toast.LENGTH_LONG).show();
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
