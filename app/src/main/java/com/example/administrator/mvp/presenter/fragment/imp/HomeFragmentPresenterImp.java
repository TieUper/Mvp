package com.example.administrator.mvp.presenter.fragment.imp;

import android.content.Context;

import com.example.administrator.mvp.common.injector.component.ContextLife;
import com.example.administrator.mvp.common.utils.MySubscriber;
import com.example.administrator.mvp.common.utils.RxUtil;
import com.example.administrator.mvp.fragment.home.IHomeFragment;
import com.example.administrator.mvp.fragment.home.IHomeTabFragment;
import com.example.administrator.mvp.model.api.ApiHomeService;
import com.example.administrator.mvp.model.entity.CategoryEntity;
import com.example.administrator.mvp.model.entity.NewsEntity;
import com.example.administrator.mvp.model.entity.RequestParam;
import com.example.administrator.mvp.presenter.fragment.HomeFragmentPresenter;
import com.example.administrator.mvp.ui.IView;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by tie on 2016/11/21.
 */

public class HomeFragmentPresenterImp implements HomeFragmentPresenter {

    private IHomeTabFragment mIHomeTabFragment;

    private Context mContext;

    private RxFragment mFragment;

    @Inject
    ApiHomeService mApiHomeService;

    @Inject
    RequestParam mRequestParam;

    @Inject
    public HomeFragmentPresenterImp(@ContextLife(value = "Activity") Context context, RxFragment fragment) {
        mContext = context;
        mFragment = fragment;
    }
//
//    @Override
//    public void getNews(String id) {
//        Map<String, String> params = mRequestParam.addParam("NewsTypeCode", id)
//                .addParam("PageSize", "20")
//                .addParam("PageIndex", "1")
//                .addParam("InCircle", "0")
//                .getParams();
//        mApiHomeService.getNews(params)
//                .compose(RxUtil.rxSchedulerHelper(mFragment))
//                .subscribe(new Subscriber<NewsEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        mIHomeTabFragment.refreshComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mIHomeTabFragment.showError();
//                    }
//
//                    @Override
//                    public void onNext(NewsEntity newsEntity) {
//                        mIHomeTabFragment.showNews(newsEntity);
//                    }
//                });
//
//    }

    @Override
    public void getNews(String id) {
        Map<String, String> params = mRequestParam.addParam("NewsTypeCode", id)
                .addParam("PageSize", "20")
                .addParam("PageIndex", "1")
                .addParam("InCircle", "0")
                .getParams();
        mApiHomeService.getNews(params)
                .compose(RxUtil.rxSchedulerHelper(mFragment))
                .subscribe(new MySubscriber<NewsEntity>(mContext) {
                    @Override
                    public void onResult(NewsEntity newsEntity) {
                        mIHomeTabFragment.showNews(newsEntity);
                    }

                    @Override
                    public void onCompleted() {
                        mIHomeTabFragment.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIHomeTabFragment.showError();
                    }
                });

    }

    @Override
    public void getMoreNews(String id,int index) {
        Map<String, String> params = mRequestParam.addParam("NewsTypeCode", id)
                .addParam("PageSize", "20")
                .addParam("PageIndex", String.valueOf(index))
                .addParam("InCircle", "0")
                .getParams();
        mApiHomeService.getNews(params)
                .compose(RxUtil.rxSchedulerHelper(mFragment))
                .subscribe(new Subscriber<NewsEntity>() {
                    @Override
                    public void onCompleted() {
                        mIHomeTabFragment.refreshComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mIHomeTabFragment.showError();
                    }

                    @Override
                    public void onNext(NewsEntity newsEntity) {
                        mIHomeTabFragment.showMore(newsEntity);
                    }
                });
    }

//    @Override
//    public void getMoreNews(String id, int index) {
//        Map<String, String> params = mRequestParam.addParam("NewsTypeCode", id)
//                .addParam("PageSize", "20")
//                .addParam("PageIndex", String.valueOf(index))
//                .addParam("InCircle", "0")
//                .getParams();
//
//        mApiHomeService.getNews(params)
//                .compose(RxUtil.rxSchedulerHelper(mFragment))
//                .subscribe(new Subscriber<NewsEntity>() {
//                    @Override
//                    public void onCompleted() {
//                        mIHomeTabFragment.refreshComplete();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mIHomeTabFragment.showError();
//                    }
//
//                    @Override
//                    public void onNext(NewsEntity newsEntity) {
//                        mIHomeTabFragment.showMoreNews(newsEntity);
//                    }
//                });
//    }

    @Override
    public void attachView(IView iView) {
        mIHomeTabFragment = (IHomeTabFragment) iView;
    }

    @Override
    public void detachView() {

    }


    @Override
    public void getCategory() {

        mApiHomeService.getCategory(mRequestParam.getParams())
                .compose(RxUtil.rxSchedulerHelper(mFragment))
                .subscribe(new Subscriber<CategoryEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(CategoryEntity categoryEntity) {
                        ((IHomeFragment)mFragment).showCategory(categoryEntity);
                    }
                });
    }
}
