package com.example.administrator.mvp.common.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.injector.component.DaggerActivityComponent;
import com.example.administrator.mvp.common.injector.module.ActivityModule;
import com.example.administrator.mvp.common.utils.DayNightHelper;
import com.example.administrator.mvp.common.widget.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by tie on 2016/9/6.
 */
public abstract class BaseActivity extends SupportActivity {

    //加载Dialog
    private LoadingDialog mLoadingDialog;
    private DayNightHelper mDayNightHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        initTheme();

        setContentView(getLayoutResID());
        //初始化Dagger2
        initDagger2();
        //夜间模式
//        AppCompatDelegate.setDefaultNightMode(
//                SharedPreferenceUtil.getNightModeState() ?
//                        AppCompatDelegate.MODE_NIGHT_YES :
//                        AppCompatDelegate.MODE_NIGHT_NO);
        //初始化UI
        initUI();
    }

    private void initDagger2() {
        ActivityComponent activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(MyApplication.getInstance().getClientAppComponent())
                .build();

        inject(activityComponent);
    }

    private void initData() {
        mDayNightHelper = new DayNightHelper(this);
    }

    private void initTheme() {
        if (mDayNightHelper.isDay()) {
            setTheme(R.style.DayTheme);
        } else {
            setTheme(R.style.NightTheme);
        }
    }



    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    /**
     * 注入
     */
    protected abstract void inject(ActivityComponent activityComponent);

    /**
     * 初始化
     */
    protected abstract void initUI();

    /**
     * getContentView ID
     *
     * @return 布局的id
     */
    protected abstract int getLayoutResID();

    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * 显示加载Dialog
     */
    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }

        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
