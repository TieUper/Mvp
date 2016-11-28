package com.example.administrator.mvp.common.utils;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.mvp.model.entity.BaseEntity;
import com.example.administrator.mvp.ui.login.imp.LoginActivity;

import rx.Subscriber;

/**
 * Created by tie on 2016/11/28.
 */

public abstract class MySubscriber<T extends BaseEntity> extends Subscriber<T> {

    public Context mContext;

    public MySubscriber() {
    }

    public MySubscriber(Context context) {
        mContext = context;
    }


    public void onNext(T t) {
        if(t.getResult() == 3) {
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
        }

        onResult(t);
    }

    public abstract void onResult(T t);
}
