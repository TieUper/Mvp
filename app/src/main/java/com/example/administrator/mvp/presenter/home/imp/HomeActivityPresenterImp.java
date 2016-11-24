package com.example.administrator.mvp.presenter.home.imp;

import android.content.Context;

import com.example.administrator.mvp.common.injector.module.PerActivity;
import com.example.administrator.mvp.common.utils.RxUtil;
import com.example.administrator.mvp.fragment.home.IHomeTabFragment;
import com.example.administrator.mvp.model.api.ApiHomeService;
import com.example.administrator.mvp.model.entity.CategoryEntity;
import com.example.administrator.mvp.model.entity.RequestParam;
import com.example.administrator.mvp.presenter.home.HomeActivityPresenter;
import com.example.administrator.mvp.ui.IView;
import com.example.administrator.mvp.ui.home.IMainActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by tie on 2016/9/13.
 */
@PerActivity
public class HomeActivityPresenterImp implements HomeActivityPresenter{

    private IMainActivity mIMainActivity;

    private RxAppCompatActivity mActivity;

    private Context mContext;
    private RxFragment mFragment;

    private IHomeTabFragment mIHomeTabFragment;

    @Inject
    RequestParam mRequestParam;

    @Inject
    ApiHomeService mApiHomeService;

//    @Inject
//    public HomeActivityPresenterImp(@ContextLife(value = "Activity") Context context, RxAppCompatActivity activity) {
//        mActivity = activity;
//        mContext = context;
//    }

    @Inject
    public HomeActivityPresenterImp(RxAppCompatActivity activity) {
        mActivity = activity;
        //registerEvent();
    }

    @Override
    public void attachView(IView iView) {
        mIMainActivity  = (IMainActivity) iView;
    }

    @Override
    public void detachView() {

    }



    @Override
    public void getCategory() {

        mApiHomeService.getCategory(mRequestParam.getParams())
                .compose(RxUtil.rxSchedulerHelper(mActivity))
                .subscribe(new Subscriber<CategoryEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(CategoryEntity categoryEntity) {
                        mIMainActivity.showCategory(categoryEntity);
                    }
                });
    }


//    public void registerEvent() {
//
//        RxBus.getDefault().toObservable(NightModeEvent.class)
//                .compose(RxUtil.<NightModeEvent>rxSchedulerHelper(mActivity))
//                .map(new Func1<NightModeEvent, Boolean>() {
//                    @Override
//                    public Boolean call(NightModeEvent nightModeEvent) {
//                        return nightModeEvent.getNightMode();
//                    }
//                })
//                .subscribe(new Observer<Boolean>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mIMainActivity.showError("切换模式失败ヽ(≧Д≦)ノ");
//                    }
//
//                    @Override
//                    public void onNext(Boolean aBoolean) {
//                        mIMainActivity.useNightMode(aBoolean);
//                    }
//                });
//    }
}
