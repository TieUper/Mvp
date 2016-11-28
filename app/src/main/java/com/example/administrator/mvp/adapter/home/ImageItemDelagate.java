package com.example.administrator.mvp.adapter.home;

import android.text.TextUtils;
import android.widget.ImageView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.entity.News;
import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;

/**
 * 大圖
 * Created by tie on 2016/11/21.
 */

public class ImageItemDelagate implements ItemViewDelegate<News>{


//    private Context mContext;
//
//    public ImageItemDelagate(Context context) {
//        mContext = context;
//    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_news_bigimage;
    }

    @Override
    public boolean isForViewType(News item, int position) {
        return item.preview.type == PreviewsType.TYPE_IMAGE;
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
        //大图
         ImageView imageView =  holder.getView(R.id.iv_icon);
        ImageLoader.load(holder.getConvertView().getContext(), news.preview.body,imageView);
        //Picasso.with(holder.getConvertView().getContext()).load(news.preview.body).into(imageView);
    }
}
