package com.example.administrator.mvp.common.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.injector.component.DaggerActivityComponent;
import com.example.administrator.mvp.common.injector.module.ActivityModule;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by tie on 2016/9/6.
 */
public abstract class BaseActivity extends RxAppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutResID());
        //初始化Dagger2
        initDagger2();
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
     * @return  布局的id
     */
    protected abstract int getLayoutResID();
}
