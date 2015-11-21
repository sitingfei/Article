package cc.wudoumi.article;

import android.app.Application;

import cc.wudoumi.article.dao.DbHelper;
import cc.wudoumi.framework.utils.NetUtil;
import cc.wudoumi.framework.utils.PreferenceHelper;

/**
 * author: qianjujun on 2015/11/12 13.
 * email:  qianjujun@imcoming.cn
 */
public class ArticleApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();




        NetUtil.getRequestManager().start(this);

        DbHelper.init(this);

        PreferenceHelper.init(this);
    }


}
