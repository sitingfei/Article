package cc.wudoumi.article.common.response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.utils.NetUtil;

/**
 * author: qianjujun on 2015/11/17 11.
 * email:  qianjujun@imcoming.cn
 */
public class LodingRequestListner extends BaseRequestListner{

    private boolean cancelAble;

    private boolean loding;

    private ProgressDialog mProgressDialog;


    public LodingRequestListner(Context context,boolean loding,boolean cancelAble,boolean tips) {
        super(context,tips);
        this.loding = loding;
        this.cancelAble = cancelAble;
    }

    public LodingRequestListner(Context context,boolean loding,boolean cancelAble) {
        this(context,loding,cancelAble,false);
    }

    public LodingRequestListner(Context context,boolean loding) {
        this(context,loding,true);
    }

    public LodingRequestListner(Context context) {
        this(context,true,true);
    }

    public LodingRequestListner(Fragment v4Fragment,boolean loding,boolean cancelAble,boolean tips) {
        this(v4Fragment.getActivity(),loding,cancelAble,tips);
    }

    public LodingRequestListner(Fragment v4Fragment,boolean loding,boolean cancelAble) {
        this(v4Fragment,loding,cancelAble,false);
    }

    public LodingRequestListner(Fragment v4Fragment,boolean loding) {
        this(v4Fragment,loding,true);
    }

    public LodingRequestListner(Fragment v4Fragment) {
        this(v4Fragment,true,true);
    }




    @Override
    public void onStart() {
        super.onStart();
        if(!loding){
            return;
        }
        mProgressDialog = ProgressDialog.show(context, "", "正在加载数据...", true, cancelAble, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                NetUtil.getRequestManager().cancel(getTag());
            }
        });

    }

    @Override
    public void onEnd(boolean success, ErrorMessage e) {
        super.onEnd(success, e);
        if(mProgressDialog!=null&&mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
