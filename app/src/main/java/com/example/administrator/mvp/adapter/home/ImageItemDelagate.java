package com.example.administrator.mvp.adapter.home;

import android.content.res.Resources;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.greendao.News;
import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;

/**
 * 大圖
 * Created by tie on 2016/11/21.
 */

public class ImageItemDelagate implements ItemViewDelegate<News> {


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
        return item.getPreview().getType() == PreviewsType.TYPE_IMAGE;
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
        holder.setText(R.id.tv_title, news.getTitle());
        //时间
        holder.setText(R.id.tv_time, news.getReleseDate());
        //是否是专题
        String topicId = news.getTopicID();
        if ((!"0".equals(topicId)) && !TextUtils.isEmpty(topicId)) {
//            holder.tv_zhuanti.setVisibility(View.VISIBLE);
            holder.setVisible(R.id.tv_zhuanti, true);
        } else {
            holder.setVisible(R.id.tv_zhuanti, false);
        }
        //大图
        ImageView imageView = holder.getView(R.id.iv_icon);
        ImageLoader.load(holder.getConvertView().getContext(), news.getPreview().getBody(), imageView);
    }
}
