package com.example.administrator.mvp.common.injector.component;

import com.example.administrator.mvp.common.injector.module.FragmentModule;
import com.example.administrator.mvp.common.injector.module.PerFragment;
import com.example.administrator.mvp.fragment.home.imp.HomeTabFragment;
import com.example.administrator.mvp.fragment.home.imp.RefreshFrgment;

import dagger.Component;

/**
 * Created by tie on 2016/9/12.
 */
@PerFragment
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(HomeTabFragment homeTabFragment);

    void inject(RefreshFrgment refreshFrgment);
}
