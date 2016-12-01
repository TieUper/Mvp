package com.example.administrator.mvp.fragment.home;

import com.example.administrator.mvp.model.greendao.Category;
import com.example.administrator.mvp.ui.IView;

import java.util.List;

/**
 * Created by tie on 2016/11/28.
 */

public interface IHomeFragment extends IView {

    void showCategory(List<Category> mCategory);
    //夜间模式切换
    void useNightMode(boolean isNight);
    //切换失败
    void showError(String message);
}
