package com.example.administrator.mvp.ui.splash.imp;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.common.utils.PackageUtils;
import com.example.administrator.mvp.model.entity.WelcomeBean;
import com.example.administrator.mvp.presenter.splash.imp.SplashPresenterImp;
import com.example.administrator.mvp.ui.home.imp.MainActivity;
import com.example.administrator.mvp.ui.splash.ISplashActivity;

import java.io.File;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.bunnyblue.droidfix.dexloader.DroidFix;

/**
 * Created by tie on 2016/9/13.
 */
public class SplashActiviy extends BaseActivity implements ISplashActivity {

    @Inject
    SplashPresenterImp mSplashPresenterImp;

    @Inject
    PackageUtils mPackageUtils;

    @Inject
    ImageLoader mImageLoader;

    @Bind(R.id.iv_welcome_bg)
    ImageView mIvWelcomeBg;
    @Bind(R.id.tv_welcome_author)
    TextView mTvWelcomeAuthor;


    @Override
    protected void inject(ActivityComponent activityComponent) {
        ButterKnife.bind(this);
        activityComponent.inject(this);
        mSplashPresenterImp.attachView(this);
    }

    @Override
    protected void initUI() {
        mSplashPresenterImp.getSplashData();

        //droidFix
        initDroidFix();
    }

    private void initDroidFix() {
        DroidFix.install(this);

        File dest=new File(getFilesDir(), DroidFix.DROID_CODE_CACHE+File.separator+"patch.apk");

        String version = mPackageUtils.getVersion();

        System.out.println("VVVVVVVVVVVVVVVVVVV" + version);

        if (dest.isFile()&&dest.exists())
        {
            DroidFix.installPatch(this, dest);
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_splash;
    }

    @Override
    public void showContent(WelcomeBean welcomeBean) {
        mImageLoader.load(this,welcomeBean.getImg(),mIvWelcomeBg);
        mIvWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        mTvWelcomeAuthor.setText(welcomeBean.getText());
    }

    @Override
    public void jumpToMain() {
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
