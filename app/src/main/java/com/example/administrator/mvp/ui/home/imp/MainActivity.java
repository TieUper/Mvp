package com.example.administrator.mvp.ui.home.imp;

import android.content.IntentFilter;
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
import com.example.administrator.mvp.common.broadcast.SMSBroadcastReceiver;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.utils.Constants;
import com.example.administrator.mvp.common.utils.DayNightHelper;
import com.example.administrator.mvp.common.utils.SharedPreferenceUtil;
import com.example.administrator.mvp.fragment.SecondFragment;
import com.example.administrator.mvp.fragment.home.imp.HomeFragment;
import com.example.administrator.mvp.presenter.home.imp.HomeActivityPresenterImp;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.example.administrator.mvp.ui.set.SettingFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity implements IMainActivity, ViewPager.OnPageChangeListener {

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
    private DayNightHelper mDayNightHelper;

    private HomeFragment mHomeFragment;
    private SettingFragment mSettingFragment;

    private int hideFragment = Constants.TYPE_MAIN;
    private int showFragment = Constants.TYPE_MAIN;

    private SMSBroadcastReceiver mSMSBroadcastReceiver;
    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private SecondFragment mSecondFragment;


    @Override
    protected void inject(ActivityComponent activityComponent) {
        ButterKnife.bind(this);
        activityComponent.inject(this);
        //mHomeActivityPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {

        //mHomeActivityPresenterImp.getCategory();
        // mHomeActivityPresenterImp.give();
        //初始化Toolbar和Drawer

        mHomeFragment = new HomeFragment();
        mSettingFragment = new SettingFragment();
        mSecondFragment = SecondFragment.newInstance("1","2");

        init();

        initSmsBroadReceiver();
    }

    private void initSmsBroadReceiver() {
//生成广播处理
        mSMSBroadcastReceiver = new SMSBroadcastReceiver();

        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter(ACTION);
        intentFilter.setPriority(Integer.MAX_VALUE);
        //注册广播
        this.registerReceiver(mSMSBroadcastReceiver, intentFilter);

        mSMSBroadcastReceiver.setOnReceivedMessageListener(new SMSBroadcastReceiver.MessageListener() {
            @Override
            public void onReceived(String message) {
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG).show();
            }
        });
    }


    private void init() {
        setToolBar(mToolBar, "主页");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLastItem = mNavigation.getMenu().findItem(R.id.drawer_zhihu);
        loadMultipleRootFragment(R.id.fl_main_content, 0, mHomeFragment, mSettingFragment,mSecondFragment);
        mNavigation.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.drawer_zhihu:
                    showFragment = Constants.TYPE_MAIN;
                    break;
                case R.id.drawer_gank:
                    showFragment = Constants.TYPE_GANK;
                    break;
                case R.id.drawer_wechat:
                    break;
                case R.id.drawer_setting:
                    showFragment = Constants.TYPE_SETTING;
                    break;
                case R.id.drawer_like:
                    break;
                case R.id.drawer_about:
                    break;
            }

            if (mLastItem != null) {
                mLastItem.setChecked(false);
            }
            mLastItem = item;

            item.setChecked(true);
            SharedPreferenceUtil.setCurrentItem(showFragment);
            mToolBar.setTitle(item.getTitle());
            mDrawerLayout.closeDrawers();
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            hideFragment = showFragment;
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
        //注销短信监听广播
        this.unregisterReceiver(mSMSBroadcastReceiver);

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_MAIN:
                return mHomeFragment;
            case Constants.TYPE_GANK:
                return mSecondFragment;
            case Constants.TYPE_SETTING:
                return mSettingFragment;
        }
        return mHomeFragment;
    }
}
