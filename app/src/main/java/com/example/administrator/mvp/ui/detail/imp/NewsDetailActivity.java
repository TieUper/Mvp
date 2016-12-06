package com.example.administrator.mvp.ui.detail.imp;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.entity.NewsDetail;
import com.example.administrator.mvp.presenter.home.imp.HomeActivityPresenterImp;
import com.example.administrator.mvp.ui.detail.INewsDetailView;
import com.victor.loading.rotate.RotateLoading;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新闻详情界面
 */
public class NewsDetailActivity extends BaseActivity implements INewsDetailView {

    @Inject
    HomeActivityPresenterImp mPresenterImp;
    @Bind(R.id.detail_bar_image)
    ImageView mDetailBarImage;
    @Bind(R.id.detail_bar_copyright)
    TextView mDetailBarCopyright;
    @Bind(R.id.wv_detail_content)
    WebView mWvDetailContent;
    @Bind(R.id.view_loading)
    RotateLoading mViewLoading;
    @Bind(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @Bind(R.id.view_toolbar)
    Toolbar viewToolbar;

    @Inject
    ImageLoader mImageLoader;
    private String mNewsId;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);

        setToolBar(viewToolbar,"");
        //webview
        initWebview();
        mNewsId = getIntent().getStringExtra("newsId");
        mViewLoading.start();

        mPresenterImp.getNewsDetail(mNewsId);
    }

    private void initWebview() {
        mWvDetailContent.getSettings().setJavaScriptEnabled(true);//启用js
        mWvDetailContent.getSettings().setBlockNetworkImage(false);//解决图片不显示

        mWvDetailContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mViewLoading.stop();
            }
        });
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_news_detail;
    }

    /**
     * 显示新闻详情
     *
     * @param news
     */
    @Override
    public void showNewsDetail(NewsDetail news) {

        NewsDetail.Content content = news.NewsInfo;

        //背景图片
        mImageLoader.load(this,content.ShareImg,mDetailBarImage);
        //标题
        clpToolbar.setTitle(content.Ttile);
        //加载webView
        mWvDetailContent.loadUrl(content.ContentCN);
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
