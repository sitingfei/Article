package cc.wudoumi.article.moudle.ui;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import cc.wudoumi.article.MainActivity;
import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.bean.DefaultImage;
import cc.wudoumi.article.common.response.BaseRequestListner;
import cc.wudoumi.article.common.response.GsonListSuccessListner;
import cc.wudoumi.article.common.util.ActivityTool;
import cc.wudoumi.article.common.util.ParemsTool;
import cc.wudoumi.article.dao.ArticleTypeDao;
import cc.wudoumi.article.dao.DbHelper;
import cc.wudoumi.article.dao.DefaultImageDao;
import cc.wudoumi.framework.interfaces.TaskWork;
import cc.wudoumi.framework.thread.MyTask;
import cc.wudoumi.framework.thread.MyTaskItem;
import cc.wudoumi.framework.utils.NetUtil;


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
        articleTypeDao = DbHelper.getInstance().getDaoSession().getArticleTypeDao();
        defaultImageDao = DbHelper.getInstance().getDaoSession().getDefaultImageDao();
        check();


    }

    private Handler mHandler = new Handler();

    private void check(){
        MyTaskItem<Boolean> myTaskItem = new MyTaskItem<>( new TaskWork<Boolean>() {
            @Override
            public Boolean doWork() {
                return articleTypeDao.queryBuilder().count()>0;
            }

            @Override
            public boolean onSuccess(Boolean aVoid) throws Exception {
                if(aVoid){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ActivityTool.turnOther(WelcomeActivity.this, MainActivity.class);
                            finish();
                        }
                    },500);
                }else{
                    doNetWork();
                }
                return aVoid;
            }
        });
        MyTask<Void> mTask = new MyTask<>(myTaskItem);
        mTask.execute();


    }

    private void doNetWork(){
        NetUtil.getRequestManager().doRequest(ParemsTool.getArticleType(),
                new BaseRequestListner(this, true),
                new GsonListSuccessListner<List<ArticleType>>(new TypeToken<List<ArticleType>>() {
                }.getType()) {
                    @Override
                    public boolean onSuccess(final List<ArticleType> articleTypes) throws Exception {
                        new Thread() {
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

        NetUtil.getRequestManager().doRequest(ParemsTool.getArticleDefaultImage(),
                new BaseRequestListner(this),
                new GsonListSuccessListner<List<DefaultImage>>(new TypeToken<List<DefaultImage>>() {
                }.getType()) {
                    @Override
                    public boolean onSuccess(final List<DefaultImage> defaultImages) throws Exception {
                        new Thread() {
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
