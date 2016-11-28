package com.example.administrator.mvp.fragment.home;

import com.example.administrator.mvp.model.entity.CategoryEntity;
import com.example.administrator.mvp.ui.IView;

/**
 * Created by tie on 2016/11/28.
 */

public interface IHomeFragment extends IView {

    void showCategory(CategoryEntity categoryEntity);
    //夜间模式切换
    void useNightMode(boolean isNight);
    //切换失败
    void showError(String message);
}
