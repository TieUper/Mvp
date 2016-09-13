package com.example.administrator.mvp.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.mvp.R;


/**
 * 网络请求，加载中dialog
 */
public class LoadingDialog extends Dialog {
    private TextView textView;
    private ProgressBar progressBar;

    public LoadingDialog(Context context) {
        this(context, R.style.Request_Dialog);

    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_loading_dialog);
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //解决加载数据出来之前按返回键无效问题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

    }


    public void setLoadingText(String text) {
        textView.setText(text);

    }

    public void toogleLoadingProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
