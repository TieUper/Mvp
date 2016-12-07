package com.example.administrator.mvp.ui.detail.imp;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.sharelb.shareboard.ShareBoardUtils;
import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.base.BaseActivity;
import com.example.administrator.mvp.common.injector.component.ActivityComponent;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.entity.NewsDetail;
import com.example.administrator.mvp.presenter.home.imp.HomeActivityPresenterImp;
import com.example.administrator.mvp.ui.detail.INewsDetailView;
import com.umeng.socialize.UMShareAPI;
import com.victor.loading.rotate.RotateLoading;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.nsv_scroller)
    NestedScrollView mNestedScrollView;

    @Inject
    ImageLoader mImageLoader;
    @Bind(R.id.tv_detail_bottom_like)
    TextView mTvDetailBottomLike;
    @Bind(R.id.tv_detail_bottom_comment)
    TextView mTvDetailBottomComment;
    @Bind(R.id.tv_detail_bottom_share)
    TextView mTvDetailBottomShare;
    @Bind(R.id.ll_detail_bottom)
    FrameLayout mLlDetailBottom;
    private String mNewsId;

    private boolean isBottomShow = true;

    //Share
    private String shareTitle;
    private String shareText;
    private String shareImage;
    private String shareUrl;

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initUI() {
        ButterKnife.bind(this);

        setToolBar(viewToolbar, "");
        //webview
        initWebview();
        mNewsId = getIntent().getStringExtra("newsId");
        mViewLoading.start();

        initListener();

        mPresenterImp.getNewsDetail(mNewsId);
    }


    private void initListener() {
        //滑动监听
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY - oldScrollY > 0 && isBottomShow) {
                    //向下滑动隐藏
                    isBottomShow = false;
                    mLlDetailBottom.animate().translationY(mLlDetailBottom.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isBottomShow) {
                    //向上滑动显示
                    isBottomShow = true;
                    mLlDetailBottom.animate().translationY(0);
                }
            }
        });

    }

    private void initWebview() {
        mWvDetailContent.getSettings().setJavaScriptEnabled(true);//启用js
        mWvDetailContent.getSettings().setBlockNetworkImage(false);//解决图片不显示

        mWvDetailContent.setWebViewClient(new WebViewClient() {
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
        mImageLoader.load(this, content.ShareImg, mDetailBarImage);
        //标题
        clpToolbar.setTitle(content.Ttile);
        //加载webView
        mWvDetailContent.loadUrl(content.ContentCN);
        //点赞数
        mTvDetailBottomLike.setText(String.format("%d个赞", Integer.parseInt(content.LikeCount)));
        //评论数
        mTvDetailBottomComment.setText(String.format("%d条评论", Integer.parseInt(content.CommentCount)));

        shareTitle = content.Ttile;
        shareText = content.ShareText;
        shareImage = content.ShareImg;
        shareUrl = content.ContentCN;
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

    @OnClick(R.id.tv_detail_bottom_share)
    public void onClick() {
        //分享
        ShareBoardUtils.getInstance(NewsDetailActivity.this).show(shareTitle,shareText,shareUrl,shareUrl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Umeng分享
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
