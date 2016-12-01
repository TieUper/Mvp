package com.example.administrator.mvp.model.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.example.administrator.mvp.model.greendao.Category;
import com.example.administrator.mvp.model.greendao.News;
import com.example.administrator.mvp.model.greendao.Preview;

import com.example.administrator.mvp.model.greendao.CategoryDao;
import com.example.administrator.mvp.model.greendao.NewsDao;
import com.example.administrator.mvp.model.greendao.PreviewDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig categoryDaoConfig;
    private final DaoConfig newsDaoConfig;
    private final DaoConfig previewDaoConfig;

    private final CategoryDao categoryDao;
    private final NewsDao newsDao;
    private final PreviewDao previewDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        categoryDaoConfig = daoConfigMap.get(CategoryDao.class).clone();
        categoryDaoConfig.initIdentityScope(type);

        newsDaoConfig = daoConfigMap.get(NewsDao.class).clone();
        newsDaoConfig.initIdentityScope(type);

        previewDaoConfig = daoConfigMap.get(PreviewDao.class).clone();
        previewDaoConfig.initIdentityScope(type);

        categoryDao = new CategoryDao(categoryDaoConfig, this);
        newsDao = new NewsDao(newsDaoConfig, this);
        previewDao = new PreviewDao(previewDaoConfig, this);

        registerDao(Category.class, categoryDao);
        registerDao(News.class, newsDao);
        registerDao(Preview.class, previewDao);
    }
    
    public void clear() {
        categoryDaoConfig.getIdentityScope().clear();
        newsDaoConfig.getIdentityScope().clear();
        previewDaoConfig.getIdentityScope().clear();
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public PreviewDao getPreviewDao() {
        return previewDao;
    }

}