package cc.wudoumi.framework.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import cc.wudoumi.framework.R;
import cc.wudoumi.framework.ioc.ViewUtils;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.NetInterface;
import cc.wudoumi.framework.net.NetInterfaceFactory;
import cc.wudoumi.framework.net.ResponseListner;
import cc.wudoumi.framework.views.loadview.EmptyLoadView;
import cc.wudoumi.framework.views.loadview.Iretry;


/**
 * author: qianjujun on 2015/9/17 16.
 * email:  qianjujun@imcoming.cn
 */
public abstract class BaseLoadingFragment extends Fragment {
    protected View cacheView;//缓存view

    protected EmptyLoadView emptyLoadView;

    private View laoutView;//

    protected NetInterface netInterface;


    private FrameLayout viewContent;

    protected RelativeLayout viewHead;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    protected abstract int getLayoutId();


    protected abstract void initView();



    protected abstract void banderDataAndListner();


    protected abstract void requestData(ResponseListner responseListner);


    protected void requestEnd(boolean success, ErrorMessage e,boolean openLoding) {

    }

    protected boolean requestSuccess(String result) throws Exception {
        return false;
    }

    private boolean openLoding = true;


    public void openLoding() {
        this.openLoding = true;
    }

    public void closeLoding() {
        this.openLoding = false;
    }


    protected void initViewHead(ViewGroup headParent) {
        View headViewContent = getViewHead();
        if(headViewContent!=null){
            headParent.addView(headViewContent);
        }
    }

    protected View getViewHead(){
        return null;
    }





    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (cacheView == null) {
            cacheView = createView(inflater, container, savedInstanceState);
            if(!loadDataEveryTime()){
                requestData(getResponseListner());
            }
        }
        ViewGroup v = (ViewGroup) cacheView.getParent();
        if (v != null) {
            v.removeView(cacheView);
        }

        if(loadDataEveryTime()){
            requestData(getResponseListner());
        }

        onCreateView(savedInstanceState);
        return cacheView;
    }

    private View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.framework_view_fragment_loding, null);

        emptyLoadView = (EmptyLoadView) view.findViewById(R.id.emptyloadview);
        emptyLoadView.setIretry(new Iretry() {
            @Override
            public void retryQuest() {
                requestData(getResponseListner());
            }
        });
        viewHead = (RelativeLayout) view.findViewById(R.id.view_head);
        viewContent = (FrameLayout) view.findViewById(R.id.content);
        laoutView = initView(inflater, viewContent);
        initView();
        banderDataAndListner();
        initViewHead(viewHead);
        return view;
    }

    private View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(getLayoutId(), container);
    }



    protected final ResponseListner getResponseListner() {

        ResponseListner responseListner = new ResponseListner(getRequestTag()) {

            @Override
            public void onStart() {
                super.onStart();
                if (openLoding) {
                    viewContent.setVisibility(View.GONE);
                    emptyLoadView.showLoading();
                }

            }

            @Override
            public boolean onSuccess(String result) throws Exception {
                return requestSuccess(result);
            }

            @Override
            public void onEnd(boolean success, ErrorMessage e) {
                super.onEnd(success, e);
                if (openLoding) {
                    if (success) {
                        viewContent.setVisibility(View.VISIBLE);
                        emptyLoadView.showSuccees();
                        closeLoding();
                    } else {
                        viewContent.setVisibility(View.GONE);
                        if (e == null) {
                            e = new ErrorMessage(ErrorMessage.TYPE_NO_DATA, "无更多数据");
                        }
                        emptyLoadView.showFail(e);
                    }
                    requestEnd(success, e,true);
                } else {
                   requestEnd(success, e,false);
                }


            }
        };
        return responseListner;
    }



    protected final View findViewById(int viewContentId){
        if(laoutView==null){
            throw new RuntimeException("please init contentView");
        }

        return laoutView.findViewById(viewContentId);
    }


    protected void onCreateView(Bundle savedInstanceState) {

    }


    protected String getRequestTag(){
        return getClass().getSimpleName();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        NetInterfaceFactory.getInterface().cancelContainTag(getRequestTag());

        if(!needCacheView()){
            cacheView = null;
        }
    }



    protected boolean needCacheView(){
        return true;
    }


    protected boolean loadDataEveryTime(){
        return false;
    }

}
