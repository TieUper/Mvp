package com.example.administrator.mvp.common.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.administrator.mvp.model.greendao.Category;
import com.example.administrator.mvp.model.greendao.CategoryDao;
import com.example.administrator.mvp.model.greendao.DaoMaster;
import com.example.administrator.mvp.model.greendao.DaoSession;

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
            categoryDao.deleteAll();
            categoryDao.insertInTx(list);
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
}
