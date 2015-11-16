package cc.wudoumi.article.moudle.ui;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import cc.wudoumi.article.MainActivity;
import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.bean.DefaultImage;
import cc.wudoumi.article.bean.SuperBean;
import cc.wudoumi.article.common.response.BaseResposeListner;
import cc.wudoumi.article.common.response.GsonListSuccessListner;
import cc.wudoumi.article.common.response.LodingResposeListner;
import cc.wudoumi.article.common.response.TipsLodingResposeListner;
import cc.wudoumi.article.common.util.ActivityTool;
import cc.wudoumi.article.common.util.CommonCache;
import cc.wudoumi.article.common.util.ParemsTool;
import cc.wudoumi.article.dao.ArticleTypeDao;
import cc.wudoumi.article.dao.DbHelper;
import cc.wudoumi.article.dao.DefaultImageDao;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.NetInterfaceFactory;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WelcomeActivity extends AppCompatActivity {
    ArticleTypeDao articleTypeDao;
    private DefaultImageDao defaultImageDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);

        check();


    }

    private Handler mHandler = new Handler();

    private void check(){
        new Thread(){
            @Override
            public void run() {
                articleTypeDao = DbHelper.getInstance().getDaoSession().getArticleTypeDao();
                defaultImageDao = DbHelper.getInstance().getDaoSession().getDefaultImageDao();
                if(articleTypeDao.queryBuilder().count()>0){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ActivityTool.turnOther(WelcomeActivity.this, MainActivity.class);
                            finish();
                        }
                    },500);
                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            doWork();
                        }
                    });
                }
            }
        }.start();
    }

    private void doWork(){
        NetInterfaceFactory.getInterface().doRequest(ParemsTool.getArticleType(),
                new TipsLodingResposeListner(this, false),
                new GsonListSuccessListner<List<ArticleType>>(new TypeToken<List<ArticleType>>() {
                }.getType()) {
                    @Override
                    public boolean onSuccess(final List<ArticleType> articleTypes) throws Exception {
                        new Thread(){
                            @Override
                            public void run() {
                                articleTypeDao.insertInTx(articleTypes);
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        ActivityTool.turnOther(WelcomeActivity.this, MainActivity.class);
                                        finish();
                                    }
                                });
                            }
                        }.start();

                        return true;
                    }
                });

        NetInterfaceFactory.getInterface().doRequest(ParemsTool.getArticleDefaultImage(),
                new LodingResposeListner(this, false),
                new GsonListSuccessListner<List<DefaultImage>>(new TypeToken<List<DefaultImage>>() {
                }.getType()) {
                    @Override
                    public boolean onSuccess(final List<DefaultImage> defaultImages) throws Exception {
                        new Thread(){
                            @Override
                            public void run() {
                                defaultImageDao.insertInTx(defaultImages);
                            }
                        }.start();

                        return true;
                    }
                });
    }
}
