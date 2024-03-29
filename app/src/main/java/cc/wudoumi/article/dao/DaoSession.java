package cc.wudoumi.article.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.bean.Article;
import cc.wudoumi.article.bean.DefaultImage;

import cc.wudoumi.article.dao.ArticleTypeDao;
import cc.wudoumi.article.dao.ArticleDao;
import cc.wudoumi.article.dao.DefaultImageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig articleTypeDaoConfig;
    private final DaoConfig articleDaoConfig;
    private final DaoConfig defaultImageDaoConfig;

    private final ArticleTypeDao articleTypeDao;
    private final ArticleDao articleDao;
    private final DefaultImageDao defaultImageDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        articleTypeDaoConfig = daoConfigMap.get(ArticleTypeDao.class).clone();
        articleTypeDaoConfig.initIdentityScope(type);

        articleDaoConfig = daoConfigMap.get(ArticleDao.class).clone();
        articleDaoConfig.initIdentityScope(type);

        defaultImageDaoConfig = daoConfigMap.get(DefaultImageDao.class).clone();
        defaultImageDaoConfig.initIdentityScope(type);

        articleTypeDao = new ArticleTypeDao(articleTypeDaoConfig, this);
        articleDao = new ArticleDao(articleDaoConfig, this);
        defaultImageDao = new DefaultImageDao(defaultImageDaoConfig, this);

        registerDao(ArticleType.class, articleTypeDao);
        registerDao(Article.class, articleDao);
        registerDao(DefaultImage.class, defaultImageDao);
    }
    
    public void clear() {
        articleTypeDaoConfig.getIdentityScope().clear();
        articleDaoConfig.getIdentityScope().clear();
        defaultImageDaoConfig.getIdentityScope().clear();
    }

    public ArticleTypeDao getArticleTypeDao() {
        return articleTypeDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public DefaultImageDao getDefaultImageDao() {
        return defaultImageDao;
    }

}
