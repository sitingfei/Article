package cc.wudoumi.article.common.util;

import android.app.Activity;
import android.util.Log;
import android.view.WindowManager;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.dao.ArticleTypeDao;
import cc.wudoumi.article.dao.DbHelper;
import cc.wudoumi.article.dao.DefaultImageDao;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by huyf on 2015/11/14.
 */
public class CommonCache {
    private static CommonCache commonCache;
    private Map<Integer, SoftReference<List<ArticleType>>> map;
    private Map<Integer, Integer> colorMap;
    private Map<Integer,String> imageMap;
    private ArticleTypeDao articleTypeDao;
    private DefaultImageDao defaultImageDao;

    private CommonCache() {

        map = new HashMap<>();
        colorMap = new HashMap<>();

        imageMap = new HashMap<>();

        articleTypeDao = DbHelper.getInstance().getDaoSession().getArticleTypeDao();
        defaultImageDao = DbHelper.getInstance().getDaoSession().getDefaultImageDao();
    }

    public static CommonCache get() {
        if (commonCache == null) {
            synchronized (CommonCache.class) {
                if (commonCache == null) {
                    commonCache = new CommonCache();
                }
            }
        }
        return commonCache;
    }

    public List<ArticleType> getCacheArticleType(int parentId) {
        List<ArticleType> data;
        if (map.containsKey(parentId)) {
            data = map.get(parentId).get();
            if (data != null && data.size() > 0) {
                return data;
            }
        }

        data = articleTypeDao.queryBuilder().where(ArticleTypeDao.Properties.ParentId.eq(parentId)).list();
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }


    public void cacheColor(int type, int color) {
        if (!colorMap.containsKey(type)) {
            colorMap.put(type, color);
        }
    }

    public int getColorByType(int type) {
        if (colorMap.containsKey(type)) {
            return colorMap.get(type);
        }
        ArticleType articleType = articleTypeDao.queryBuilder().where(ArticleTypeDao.Properties.Id.eq(type)).unique();
        if (articleType != null) {
            return ColorTool.getColor(articleType.getColor());
        }
        return 0;
    }


    Map<Integer, String> nameMap = new HashMap();

    public String getName(int typeId) {
        if (nameMap.containsKey(typeId)) {
            return nameMap.get(typeId);
        }

        ArticleType articleType = articleTypeDao.queryBuilder().where(ArticleTypeDao.Properties.Id.eq(typeId)).unique();
        if (articleType != null) {
            return articleType.getName();
        }

        return "******";
    }

    private int screenWidth = -1;

    public int getScreenWidth(Activity activity) {
        if (screenWidth == -1) {
            WindowManager wm = activity.getWindowManager();
            screenWidth = wm.getDefaultDisplay().getWidth();
        }
        return screenWidth;
    }



    public String getImageUrl(int typeId){
        if(!imageMap.containsKey(typeId)){
            String url =  defaultImageDao.queryBuilder().where(DefaultImageDao.Properties.TypeId.eq(typeId)).unique().getUrl();
            imageMap.put(typeId,UrlTool.IMAGE_BASE_URL+url);
        }

        return imageMap.get(typeId);
    }

}
