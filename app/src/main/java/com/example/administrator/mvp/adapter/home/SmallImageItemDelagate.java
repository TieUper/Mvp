package com.example.administrator.mvp.adapter.home;


import android.text.TextUtils;
import android.widget.ImageView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.entity.News;
import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;

/**
 * 小圖模式
 * Created by tie on 2016/11/21.
 */

public class SmallImageItemDelagate implements ItemViewDelegate<News>{



    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news_simple_small_image_info;
    }

    @Override
    public boolean isForViewType(News item, int position) {
        return item.preview.type == PreviewsType.TYPE_SMALL;
    }

    @Override
    public void convert(ViewHolder holder, News news, int position) {
        //标题
        holder.setText(R.id.tv_title,news.title);
        //时间
        holder.setText(R.id.tv_time, news.releseDate);
        //是否是专题
        String topicId = news.topicId;
        if ((!"0".equals(topicId)) && !TextUtils.isEmpty(topicId)) {
//            holder.tv_zhuanti.setVisibility(View.VISIBLE);
            holder.setVisible(R.id.tv_zhuanti,true);
        } else {
            holder.setVisible(R.id.tv_zhuanti,false);
        }
//        R.id.img
        ImageView imageView =  holder.getView(R.id.img);
        ImageLoader.load(holder.getConvertView().getContext(), news.preview.body,imageView);
    }
}
