package cc.wudoumi.article.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import cc.wudoumi.article.common.util.CommonCache;
import cc.wudoumi.framework.base.BaseLoadingFragment;
import cc.wudoumi.framework.net.NetInterfaceFactory;

/**
 * author: qianjujun on 2015/11/12 16.
 * email:  qianjujun@imcoming.cn
 */
public abstract class ArticleBaseLodingFragment extends BaseLoadingFragment{


    public static final String TAG = "QIANJUJUN";
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d(TAG,"onAttach:"+getRequestTag());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netInterface = NetInterfaceFactory.getInterface();

        Log.d(TAG,"onCreate:"+getRequestTag());
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        Log.d(TAG, "onCreateView:" + getRequestTag());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:" + getRequestTag());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:" + getRequestTag());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView:" + getRequestTag());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:" + getRequestTag());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach:" + getRequestTag());
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG, "onHiddenChanged:" + getRequestTag() + "hidden:"+ hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:" + getRequestTag());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:" + getRequestTag());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint:" + getRequestTag() +"isVisibleToUser:"+ isVisibleToUser);

    }


    @Override
    protected boolean needCacheView() {
        return true;
    }

    @Override
    protected boolean loadDataEveryTime() {
        return false;
    }



}
