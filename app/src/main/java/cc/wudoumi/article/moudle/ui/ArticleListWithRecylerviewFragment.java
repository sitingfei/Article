package cc.wudoumi.article.moudle.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
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
import cc.wudoumi.article.moudle.adapter.ArticleAdapter;
import cc.wudoumi.article.moudle.adapter.ArticleListAdapter;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.RequestParem;
import cc.wudoumi.framework.net.ResponseListner;
import cc.wudoumi.framework.views.listview.pullable.PullToRefreshLayout;
import cc.wudoumi.framework.views.listview.pullable.State;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListWithRecylerviewFragment extends ArticleBaseLodingFragment {



    private RecyclerView mListView;
    private PullToRefreshLayout mPullToRefreshLayout;


    private int childTypeId;

    private int page = 1;

    private List<Article> articleList = new ArrayList<>();
    private ArticleAdapter articleAdapter;

    private ScaleInAnimationAdapter scaleAdapter;

    private int scrrenWidth;
    public ArticleListWithRecylerviewFragment() {
        // Required empty public constructor
    }


    public static ArticleListWithRecylerviewFragment getInstance(int childTypeId) {
        ArticleListWithRecylerviewFragment articleListFragment = new ArticleListWithRecylerviewFragment();
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
        return R.layout.fragment_article_list_with_recylerview;
    }

    @Override
    protected void initView() {
        mListView = (RecyclerView) findViewById(R.id.listview);


        mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pullLayou);


    }

    @Override
    protected void banderDataAndListner() {
        mListView.setItemAnimator(new FadeInAnimator());
        articleAdapter = new ArticleAdapter(getActivity(),articleList,childTypeId);
        AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(articleAdapter);
        scaleAdapter = new ScaleInAnimationAdapter(alphaAdapter);
        mListView.setAdapter(scaleAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(linearLayoutManager);
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
    protected void requestData(ResponseListner responseListner) {
        getArticleListByNet(responseListner);
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

    private void getArticleListByNet(ResponseListner responseListner) {
        RequestParem requestParem = ParemsTool.getArticleList(childTypeId, page);
        netInterface.doRequest(requestParem, responseListner,
                new GsonListSuccessListner<List<Article>>(new TypeToken<List<Article>>() {
                }.getType()) {
                    @Override
                    public boolean onSuccess(List<Article> articles) throws Exception {
                        if (page == 1) {
                            articleList.clear();
                            articleList.add(new Article());
                        }
                        int size = articleList.size();

                        articleList.addAll(articles);
                        scaleAdapter.notifyDataSetChanged();
                       // mListView.scrollToPosition(size);
                        return true;
                    }
                });
    }

    private void getArticleListByNet() {
        getArticleListByNet(getResponseListner());
    }


    @Override
    public String getRequestTag() {
        return super.getRequestTag() + childTypeId;
    }


}
