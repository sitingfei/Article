package cc.wudoumi.article;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.common.base.ArticleBaseFragment;
import cc.wudoumi.article.common.eventbus.ChangeArticleType;
import cc.wudoumi.article.common.util.ColorTool;
import cc.wudoumi.article.common.util.CommonCache;
import cc.wudoumi.article.common.util.Contants;
import cc.wudoumi.article.dao.ArticleTypeDao;
import cc.wudoumi.article.dao.DbHelper;
import cc.wudoumi.article.moudle.adapter.MainPagerAdapter;
import cc.wudoumi.framework.utils.PreferenceHelper;
import cc.wudoumi.framework.views.PagerSlidingTabStrip;
import de.greenrobot.event.EventBus;

/**
 * Created by qianjujun on 2015/6/24 0024 10:25.
 * qianjujun@163.com
 */
public class MainFragment extends ArticleBaseFragment {

    PagerSlidingTabStrip mPagerSlidingTabStrip;
    ViewPager viewPager;


    List<ArticleType> articleTypes = new ArrayList<>();
    MainPagerAdapter mainPagerAdapter;

    private int cuurentColor;

    private FragmentManager mFragmentManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof ColorChange) {
            this.colorChange = (ColorChange) getActivity();
        }
        cuurentColor = ColorTool.getColor(R.color.default_color, getActivity());
        colorChange.onColorChange(cuurentColor,ColorTool.colorBurn(cuurentColor));
        EventBus.getDefault().register(this);
        mFragmentManager = getChildFragmentManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * EventBus
     * 当侧边栏类别变化时执行
     *
     * @param changeArticleType
     */
    public void onEventMainThread(ChangeArticleType changeArticleType) {
        updateData(changeArticleType.getParentTypeId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.view);
    }

    @Override
    protected void banderDataAndListner() {
        mPagerSlidingTabStrip.setOnTextSelectChange(new PagerSlidingTabStrip.OnTextSelectChange() {
            @Override
            public int getSelectColor() {
                return cuurentColor;
            }

            @Override
            public int getNormalColor() {
                return getColor(R.color.darkgray);
            }
        });

        mainPagerAdapter = new MainPagerAdapter(this, articleTypes);
        viewPager.setAdapter(mainPagerAdapter);
        mPagerSlidingTabStrip.setViewPager(viewPager);


        mPagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cuurentColor = getColor(articleTypes.get(position).getColor());
                mPagerSlidingTabStrip.setIndicatorColor(cuurentColor);
                colorChange.onColorChange(cuurentColor, ColorTool.colorBurn(cuurentColor));

                CommonCache.get().cacheColor(articleTypes.get(position).getId(), cuurentColor);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int parentId = PreferenceHelper.getInstance().getInt(Contants.ARTICLE_TYPE_PARENT_ID, -1);
        if (parentId == -1) {
            parentId = CommonCache.get().getCacheArticleType(0).get(0).getId();
        }
        updateData(parentId);
    }

    private void updateData(int parentTypeId) {

        articleTypes.clear();
        articleTypes.addAll(CommonCache.get().getCacheArticleType(parentTypeId));
        cuurentColor = getColor(articleTypes.get(0).getColor());
        CommonCache.get().cacheColor(articleTypes.get(0).getId(), cuurentColor);
        //.notifyDataSetChanged();
        mainPagerAdapter = new MainPagerAdapter(this,articleTypes);
        viewPager.setAdapter(mainPagerAdapter);
        mPagerSlidingTabStrip.setViewPager(viewPager);
        colorChange.onColorChange(cuurentColor, ColorTool.colorBurn(cuurentColor));
        mPagerSlidingTabStrip.setIndicatorColor(cuurentColor);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    public int getColor(int colorRes) {
        return getActivity().getResources().getColor(colorRes);
    }


    public int getColor(String color) {
        return ColorTool.getColor(color);
    }


    private int[] colors;

    private final String[] TITLES = {"红色", "橙色", "黄色", "绿色", "蓝色", "青色", "紫色"};

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(cuurentColor);


        mPagerSlidingTabStrip.setTextColor(Color.WHITE);
    }


//    /**
//     * 界面颜色的更改
//     */
//    @SuppressLint("NewApi")
//    private void colorChange(int position) {
//        // 用来提取颜色的Bitmap
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                BlankFragment.getBackgroundBitmapPosition(position));
//        // Palette的部分
//
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                Palette.Swatch vibrant = palette.getVibrantSwatch();
//                if (colorChange != null) {
//                    colorChange.onColorChange(vibrant.getRgb(), colorBurn(vibrant.getRgb()));
//                }
//            }
//        });
//
//
//    }


    public interface ColorChange {
        void onColorChange(int color, int darkColor);
    }

    private ColorChange colorChange;


//    if (getActivity() instanceof ColorChange) {
//        this.colorChange = (ColorChange) getActivity();
//    }
//
//    colors = new int[]{getColor(R.color.red), getColor(R.color.lightsalmon),
//            getColor(R.color.yellow), getColor(R.color.green), getColor(R.color.blue),
//            getColor(R.color.sienna),getColor(R.color.darkviolet)};


    //                Log.d("MainFragment","onPageScrolled:"+"position--"+position+"positionOffset--"+positionOffset);
//                if(first){
//                    first = false;
//                    right = Math.round(positionOffset)==0;
//                    int next ;
//                    if(right){
//                        next = position+1<colors.length?position+1:position;
//                    }else{
//                        next = position-1>=0?position-1:0;
//                    }
//                    currentColor = colors[position];
//                    nextColor = colors[next];
//
//                    Log.d("QIANJUJUN","offset:"+Math.round(positionOffset)+TITLES[position] + TITLES[next] + "position"+position);
//                }
//                prePositionOffet = positionOffset;
//
////                if(Math.abs(positionOffset-prePositionOffet)>0.5){
////                    right = Math.round(positionOffset)==0;
////                    int next ;
////                    if(right){
////                        next = position+1<colors.length?position+1:position;
////                    }else{
////                        next = position-1>=0?position-1:0;
////                    }
////                    nextColor = colors[next];
////                }
//
//                int resultColor = (Integer) evaluator.evaluate(positionOffset, currentColor,nextColor);
//                if(positionOffset==0&&prePositionOffet>0.9){
//                    resultColor = nextColor;
//                }
//                int dark = colorBurn(resultColor);
//
//                if (colorChange != null) {
//                    colorChange.onColorChange(resultColor, colorBurn(dark));
//                }


    //        mPagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (colorChange != null) {
//                    colorChange.onColorChange(colors[position], colorBurn(colors[position]));
//                }
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
}
