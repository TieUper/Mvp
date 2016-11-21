package com.example.administrator.mvp.fragment.home;

import com.example.administrator.mvp.model.entity.NewsEntity;
import com.example.administrator.mvp.ui.IView;

/**
 * Created by tie on 2016/9/12.
 */
public interface IHomeTabFragment extends IView{

    void showNews(NewsEntity newsEntity);
    //刷新完成
    void refreshComplete();

    //刷新出错
    void showError();
}
