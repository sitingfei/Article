package cc.wudoumi.article.moudle.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cc.wudoumi.article.R;
import cc.wudoumi.article.bean.Article;
import cc.wudoumi.article.common.base.ArticleBaseLodingFragment;
import cc.wudoumi.article.common.response.GsonListSuccessListner;
import cc.wudoumi.article.common.util.CommonCache;
import cc.wudoumi.article.common.util.ParemsTool;
import cc.wudoumi.article.moudle.adapter.ArticleListAdapter;
import cc.wudoumi.framework.interfaces.RequestListner;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.RequestParem;
import cc.wudoumi.framework.views.listview.pullable.PullToRefreshLayout;
import cc.wudoumi.framework.views.listview.pullable.State;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends ArticleBaseLodingFragment {

    private ListView mListView;
    private PullToRefreshLayout mPullToRefreshLayout;

    ImageView imageView;

    private int childTypeId;

    private int page = 1;

    private List<Article> articleList = new ArrayList<>();
    private ArticleListAdapter articleListAdapter;

    private int scrrenWidth;

    public static ArticleListFragment getInstance(int childTypeId) {
        ArticleListFragment articleListFragment = new ArticleListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("childTypeId", childTypeId);
        articleListFragment.setArguments(bundle);

        return articleListFragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        childTypeId = getArguments().getInt("childTypeId");

        scrrenWidth = CommonCache.get().getScreenWidth(getActivity());

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    protected void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, scrrenWidth * 3 / 5));

        mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pullLayou);


    }

    @Override
    protected void banderDataAndListner() {
        Glide.with(this)
                .load(CommonCache.get().getImageUrl(childTypeId))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        mListView.addHeaderView(imageView);
        articleListAdapter = new ArticleListAdapter(articleList, getActivity());
        mListView.setAdapter(articleListAdapter);

        mPullToRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                page = 1;
                getArticleListByNet();
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                getArticleListByNet();
            }
        });

        mPullToRefreshLayout.setHfBackgroundColor(CommonCache.get().getColorByType(childTypeId));
    }

    @Override
    protected void requestData(RequestListner requestListner) {
        getArticleListByNet(requestListner);
    }

    @Override
    protected void requestEnd(boolean success, ErrorMessage e, boolean openLoading) {
        super.requestEnd(success, e, openLoading);
        Log.d("ArticleListFragment", "requestEnd:" + e + success);
        if (!openLoading) {
            if (page == 1) {
                Log.d("ArticleListFragment", "-----------------------------------------");
                mPullToRefreshLayout.refreshFinish(success ? State.SUCCEED : State.FAIL);
            } else {
                mPullToRefreshLayout.loadmoreFinish(success ? State.SUCCEED : State.FAIL);
            }
        }

        if (success) {
            page++;
        }
    }

    private void getArticleListByNet(RequestListner requestListner) {
        RequestParem requestParem = ParemsTool.getArticleList(childTypeId, page);
        requestManager.doRequest(requestParem, requestListner,
                new GsonListSuccessListner<List<Article>>(new TypeToken<List<Article>>() {
                }.getType()) {
                    @Override
                    public boolean onSuccess(List<Article> articles) throws Exception {
                        if (page == 1) {
                            articleList.clear();
                        }
                        int size = articleList.size();
                        articleList.addAll(articles);
                        articleListAdapter.notifyDataSetChanged();
                        mListView.setSelection(size);
                        return true;
                    }
                });
    }

    private void getArticleListByNet() {
        getArticleListByNet(getRequestListner());
    }


    @Override
    public String getRequestTag() {
        return super.getRequestTag() + childTypeId;
    }






    boolean first = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (first) {
            first = false;
        }
        super.setUserVisibleHint(isVisibleToUser);

    }
}
