package com.example.administrator.mvp.common.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.administrator.mvp.model.entity.NewsEntity;
import com.example.administrator.mvp.model.greendao.Category;
import com.example.administrator.mvp.model.greendao.CategoryDao;
import com.example.administrator.mvp.model.greendao.DaoMaster;
import com.example.administrator.mvp.model.greendao.DaoSession;
import com.example.administrator.mvp.model.greendao.News;
import com.example.administrator.mvp.model.greendao.NewsDao;
import com.example.administrator.mvp.model.greendao.Preview;
import com.example.administrator.mvp.model.greendao.PreviewDao;

import java.util.List;

import javax.inject.Inject;

/**
 * 数据库工具类
 * Created by tie on 2016/12/1.
 */

public class DbUtils {

    private Context mContext;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @Inject
    public DbUtils(@NonNull Context context) {
        mContext = context.getApplicationContext();
        initGreenDao();
    }

    /**
     * 初始化数据库
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext, Constants.DB_NAME, null);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 保存分类信息
     * @param list 分类list集合
     */
    public void saveCategory(List<Category> list) {
        if(list.size() == 0) {
            return;
        }else{
            CategoryDao categoryDao = mDaoSession.getCategoryDao();
            categoryDao.insertOrReplaceInTx(list);
        }
    }

    /**
     * 获取分类信息
     * @return 分类集合
     */
    public List<Category> getCategory(){
        CategoryDao categoryDao = mDaoSession.getCategoryDao();
        return categoryDao.queryBuilder().build().list();
    }

    /**
     *保存新闻
     * @param list 新闻集合
     * @param categoryId 类型id
     */
    public void saveNews(List<News> list,Long categoryId) {
        if (list.size() == 0) {
            return;
        }else{
            NewsDao newsDao = mDaoSession.getNewsDao();
            PreviewDao previewDao = mDaoSession.getPreviewDao();
            for (News news : list) {
                news.setCategoryId(categoryId);
                newsDao.insertOrReplace(news);
                Preview preview = news.getPreview();
                preview.setNewsID(news.getNewsID());
                previewDao.insertOrReplace(preview);
            }
        }
    }

    /**
     * 根据类型id获取新闻列表
     * @param categoryId 频道id
     * @return
     */
    public NewsEntity getNews(Long categoryId) {
        NewsDao newsDao = mDaoSession.getNewsDao();
        PreviewDao previewDao = mDaoSession.getPreviewDao();
        List<News> list = newsDao.queryBuilder().where(NewsDao.Properties.CategoryId.eq(categoryId)).orderDesc(NewsDao.Properties.ReleseDate)
                .limit(20).list();
        for(News news : list){
            Preview preview = previewDao.queryBuilder().where(PreviewDao.Properties.NewsID.eq(news.getNewsID())).unique();
            news.setPreview(preview);
        }
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.list = list;
        return newsEntity;
    }


}
