package com.example.administrator.mvp.adapter.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.mvp.R;
import com.example.administrator.mvp.common.utils.ImageLoader;
import com.example.administrator.mvp.model.greendao.News;

import java.util.ArrayList;

public class NewsImageAdapter extends BaseAdapter {
    private ArrayList<String> iamgelist = new ArrayList<String>();  //保存图片URL
    private Context context;
    private News mNews;
    private int mPostion;

    public NewsImageAdapter(Context context) {

        this.context = context;
    }

    public NewsImageAdapter(Context context, News news) {
        this.context = context;
        mNews = news;
        //将图片放入集合中
        iamgelist.add(mNews.getPreview().getBody());
        iamgelist.add(mNews.getPreview().getBody1());
        iamgelist.add(mNews.getPreview().getBody2());

    }

    @Override
    public int getCount() {
        return iamgelist.size();
    }

    @Override
    public Object getItem(int position) {
        return iamgelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String url = (String) getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.load(context, url,viewHolder.gr_imageview);
        //Picasso.with(context).load(url).resize(640, 320).centerCrop().into(viewHolder.gr_imageview);


        return convertView;
    }


    class ViewHolder {
        ImageView gr_imageview;

        public ViewHolder(View convertView) {
            gr_imageview = (ImageView) convertView.findViewById(R.id.gr_imageview);
        }
    }
}
