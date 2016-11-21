package com.example.administrator.mvp.adapter.home;

import android.content.Context;

import com.example.administrator.mvp.model.entity.News;
import com.zhy.adapter.abslistview.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by tie on 2016/11/21.
 */

public class NewsAdapter  extends MultiItemTypeAdapter<News>{

    public NewsAdapter(Context context, List<News> datas) {
        super(context, datas);

        addItemViewDelegate(new ImageItemDelagate());
        addItemViewDelegate(new ThreeImageItemDelagate());
        addItemViewDelegate(new SmallImageItemDelagate());

    }
}
