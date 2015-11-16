package cc.wudoumi.article.common.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cc.wudoumi.article.R;


/**
 * author: qianjujun on 2015/11/8 21.
 * email:  qianjujun@imcoming.cn
 */
public abstract class ArticleBaseActivity extends AppCompatActivity{

    protected Toolbar mToolbar;

    protected FrameLayout mContentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mContentView = (FrameLayout) findViewById(R.id.content);

        LayoutInflater.from(this).inflate(getLayoutId(),mContentView);

        handlerIntentData(getIntent().getExtras());

        initToolBar();

        initView();

        banderDataAndListner();

    }


    protected abstract int getLayoutId();






    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getToolBarTitle());
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }



    protected void handlerIntentData(Bundle bundle){

    }

    protected abstract void initView();


    protected abstract void banderDataAndListner();

    protected abstract String getToolBarTitle();





    @Override
    public boolean onKeyUp(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                return true;
        }

        return super.onKeyUp(keycode, e);
    }




}
