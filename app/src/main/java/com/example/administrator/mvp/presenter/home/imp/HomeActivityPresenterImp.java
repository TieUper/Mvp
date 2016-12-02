package com.example.administrator.mvp.presenter.home.imp;

import com.example.administrator.mvp.common.injector.module.PerActivity;
import com.example.administrator.mvp.common.utils.MySubscriber;
import com.example.administrator.mvp.common.utils.RxUtil;
import com.example.administrator.mvp.model.api.ApiHomeService;
import com.example.administrator.mvp.model.entity.NewsDetail;
import com.example.administrator.mvp.model.entity.RequestParam;
import com.example.administrator.mvp.presenter.home.HomeActivityPresenter;
import com.example.administrator.mvp.ui.IView;
import com.example.administrator.mvp.ui.detail.INewsDetailView;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * Created by tie on 2016/9/13.
 */
@PerActivity
public class HomeActivityPresenterImp implements HomeActivityPresenter{

    private IMainActivity mIMainActivity;

    private RxAppCompatActivity mActivity;

    private INewsDetailView mNewsDetailView;

    @Inject
    RequestParam mRequestParam;

    @Inject
    ApiHomeService mApiHomeService;

    @Inject
    public HomeActivityPresenterImp(RxAppCompatActivity activity) {
        mActivity = activity;
        initView(activity);
    }

    private void initView(RxAppCompatActivity activity) {
        if(activity instanceof INewsDetailView) {
            mNewsDetailView = (INewsDetailView) activity;
        }else if(activity instanceof IMainActivity){
            mIMainActivity = (IMainActivity) activity;
        }

    }

    @Override
    public void attachView(IView iView) {
    }

    @Override
    public void detachView() {

    }

    /**
     * 获取新闻详情
     * @param newsId 新闻id
     */
    @Override
    public void getNewsDetail(String newsId) {
        mRequestParam.addParam("NewsID",newsId);
        mApiHomeService.getNewsDetail(mRequestParam.getParams())
                .compose(RxUtil.rxSchedulerHelper(mActivity))
                .subscribe(new MySubscriber<NewsDetail>() {
                    @Override
                    public void onResult(NewsDetail newsDetail) {
                        mNewsDetailView.showNewsDetail(newsDetail);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
