package cc.wudoumi.article;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cc.wudoumi.article.bean.ArticleType;
import cc.wudoumi.article.common.eventbus.ChangeArticleType;
import cc.wudoumi.article.common.util.CommonCache;
import cc.wudoumi.article.common.util.Contants;
import cc.wudoumi.article.dao.ArticleTypeDao;
import cc.wudoumi.article.dao.DbHelper;
import cc.wudoumi.article.moudle.adapter.MenuAdapter;
import cc.wudoumi.framework.base.BaseFragment;
import cc.wudoumi.framework.ioc.annotation.event.EventBase;
import cc.wudoumi.framework.utils.PreferenceHelper;
import de.greenrobot.event.EventBus;


/**
 * Created by qianjujun on 2015/6/24 0024 10:26.
 * qianjujun@163.com
 */
public class SlideFragment extends BaseFragment {


    private List<ArticleType> menuItems = new ArrayList<>();

    private ListView listView;

    private MenuAdapter menuAdapter;

    private MainActivity mainActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_slide;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
    }

    @Override
    protected void banderDataAndListner() {
        menuItems = CommonCache.get().getCacheArticleType(0);

        menuAdapter = new MenuAdapter(menuItems, getActivity());

        listView.setAdapter(menuAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.notifyDataSetChanged(position);

                mainActivity.closeDrawer();


                EventBus.getDefault().post(new ChangeArticleType(menuItems.get(position).getId()));

            }
        });


        int position = PreferenceHelper.getInstance().getInt(Contants.ARTICLE_TYPE_POSITION, 0);
        if (position != 0) {
            menuAdapter.notifyDataSetChanged(position);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PreferenceHelper.getInstance().put(Contants.ARTICLE_TYPE_POSITION, menuAdapter.getCurrentPosition());
        PreferenceHelper.getInstance().put(Contants.ARTICLE_TYPE_PARENT_ID, menuAdapter.getItem(menuAdapter.getCurrentPosition()).getId());
    }
}
