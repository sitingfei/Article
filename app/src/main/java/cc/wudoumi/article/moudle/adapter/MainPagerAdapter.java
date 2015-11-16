package cc.wudoumi.article.moudle.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.moudle.ui.ArticleListFragment;
import cc.wudoumi.article.moudle.ui.ArticleListWithRecylerviewFragment;

/**
 * author: qianjujun on 2015/11/12 16.
 * email:  qianjujun@imcoming.cn
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<ArticleType> articleTypes;
    public MainPagerAdapter(Fragment fragment,List<ArticleType> articleTypes) {
        super(fragment.getChildFragmentManager());

        this.articleTypes = articleTypes;
    }

    @Override
    public Fragment getItem(int position) {

        //return BlankFragment.newInstance(position);

        //return ArticleListFragment.getInstance(articleTypes.get(position).getId());
        return ArticleListWithRecylerviewFragment.getInstance(articleTypes.get(position).getId());
    }

    @Override
    public int getCount() {
        return articleTypes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return articleTypes.get(position).getName();
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        ArticleListFragment fragment = (ArticleListFragment) super.instantiateItem(container, position);
//        Log.d("QIANJUJUN","instantiateItem:"+fragment.getRequestTag());
//        int childId = articleTypes.get(position).getId();
//
//
//        if(fragment.getChildTypeId()!=childId){
//            return getItem(position);
//        }
//
//
//        return fragment;
//    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public long getItemId(int position) {
        return articleTypes.get(position).getId();
    }
}
