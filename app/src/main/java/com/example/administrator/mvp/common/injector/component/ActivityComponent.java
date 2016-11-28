package com.example.administrator.mvp.common.injector.component;

import com.example.administrator.mvp.common.injector.module.ActivityModule;
import com.example.administrator.mvp.common.injector.module.PerActivity;
import com.example.administrator.mvp.ui.home.imp.CalendarActivity;
import com.example.administrator.mvp.ui.home.imp.MainActivity;
import com.example.administrator.mvp.ui.splash.imp.SplashActiviy;

import dagger.Component;

/**
 * Created by tie on 2016/9/6.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(SplashActiviy splashActiviy);

    void inject(CalendarActivity calendarActivity);
}