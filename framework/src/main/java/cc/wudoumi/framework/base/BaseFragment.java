package cc.wudoumi.framework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * author: qianjujun on 2015/9/17 15.
 * email:  qianjujun@imcoming.cn
 */
public abstract class BaseFragment extends Fragment {
    protected View cacheView;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (cacheView == null) {
            cacheView = initView(inflater, container, savedInstanceState);
            //ViewUtils.inject(this, cacheView);
            initView();
            banderDataAndListner();
        }
        ViewGroup v = (ViewGroup) cacheView.getParent();
        if (v != null) {
            v.removeView(cacheView);
        }

        onCreateView(savedInstanceState);
        return cacheView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(!needCacheView()){
            cacheView = null;
        }
    }

    protected boolean needCacheView(){
        return true;
    }

    /**
     * 保证生命周期函数可以回调
     */
    protected void onCreateView(Bundle savedInstanceState){

    }






    protected View initView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(getLayoutId(),null);
    }

    protected abstract int getLayoutId();



    protected abstract void initView();


    protected abstract void banderDataAndListner();

    protected final View findViewById(int viewId){
        if(cacheView==null){
            throw new RuntimeException("请初始化ViewContent");
        }

        return cacheView.findViewById(viewId);
    }



}
