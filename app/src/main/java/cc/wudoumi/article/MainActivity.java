package cc.wudoumi.article;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Window;

import cc.wudoumi.article.common.base.ArticleBaseActivity;
import cc.wudoumi.article.common.util.ColorTool;


public class MainActivity extends ArticleBaseActivity implements MainFragment.ColorChange{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;






    @Override
    protected String getToolBarTitle() {
        return "游客";
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.load_fail, R.string.load_succeed);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);


    }

    @Override
    protected void banderDataAndListner() {
       // int color = ColorTool.getColor(R.color.default_color, this);
       // updateColor(color, ColorTool.colorBurn(color));
    }


    public void closeDrawer(){
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }



    @Override
    public void onColorChange(int color, int darkColor) {
        updateColor(color, darkColor);
    }

    private void updateColor(int color, int darkColor){
        mToolbar.setBackgroundColor(color);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            // 很明显，这两货是新API才有的。
            window.setStatusBarColor(darkColor);
            window.setNavigationBarColor(darkColor);
        }
    }



}
