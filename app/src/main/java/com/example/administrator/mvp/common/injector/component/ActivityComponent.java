package com.example.administrator.mvp.common.injector.component;

import com.example.administrator.mvp.MainActivity;
import com.example.administrator.mvp.common.injector.module.ActivityModule;
import com.example.administrator.mvp.common.injector.module.PerActivity;

import dagger.Component;

/**
 * Created by tie on 2016/9/6.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
}
