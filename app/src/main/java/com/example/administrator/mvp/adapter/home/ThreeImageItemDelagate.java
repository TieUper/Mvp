package com.example.administrator.mvp.adapter.home;


import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.model.greendao.News;
import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;

/**
 * 三图模式
 * Created by tie on 2016/11/21.
 */

public class ThreeImageItemDelagate implements ItemViewDelegate<News>{
    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news_bigimage;
    }

    @Override
    public boolean isForViewType(News item, int position) {
        return item.getPreview().getType() == PreviewsType.TYPE_THREE;
    }

    @Override
    public void convert(ViewHolder holder, News news, int position) {

        //夜间模式
        TypedValue background = new TypedValue();//背景色
        TypedValue textColor = new TypedValue();//字体颜色
        TypedValue textClick = new TypedValue();//字体颜色
        Resources.Theme theme = holder.getConvertView().getContext().getTheme();
        theme.resolveAttribute(R.attr.clockBackground, background, true);
        theme.resolveAttribute(R.attr.clockTextColor, textColor, true);
        theme.resolveAttribute(R.attr.clickColor, textClick, true);
        Resources resources = holder.getConvertView().getContext().getResources();
        holder.getView(R.id.ll).setBackgroundResource(background.resourceId);

        if(news.isClick()) {
            ((TextView)holder.getView(R.id.tv_title)).setTextColor(resources.getColor(textClick.resourceId));
            ((TextView)holder.getView(R.id.tv_time)).setTextColor(resources.getColor(textClick.resourceId));
        }else {
            ((TextView)holder.getView(R.id.tv_title)).setTextColor(resources.getColor(textColor.resourceId));
            ((TextView)holder.getView(R.id.tv_time)).setTextColor(resources.getColor(textColor.resourceId));
        }
        //标题
        holder.setText(R.id.tv_title,news.getTitle());
        //时间
        holder.setText(R.id.tv_time, news.getReleseDate());
        //是否是专题
        String topicId = news.getTopicID();
        if ((!"0".equals(topicId)) && !TextUtils.isEmpty(topicId)) {
//            holder.tv_zhuanti.setVisibility(View.VISIBLE);
            holder.setVisible(R.id.tv_zhuanti,true);
        } else {
            holder.setVisible(R.id.tv_zhuanti,false);
        }
        //隐藏大图
        holder.setVisible(R.id.layout_img,false);
        //显示gridView
        holder.setVisible(R.id.gridview,true);
        GridView gridView = holder.getView(R.id.gridview);
        gridView.setNumColumns(3);

        gridView.setAdapter(new NewsImageAdapter(holder.getConvertView().getContext(), news));
    }
}
