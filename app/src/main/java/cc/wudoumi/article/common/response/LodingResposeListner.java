package cc.wudoumi.article.common.response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.NetInterface;
import cc.wudoumi.framework.net.NetInterfaceFactory;
import cc.wudoumi.framework.net.ParseDataException;


/**
 * author: qianjujun on 2015/10/9 16.
 * email:  qianjujun@imcoming.cn
 *
 * 实现加载框的网络回调接口（若可以取消，则取消加载框时自动断开回调接口）
 */
public class LodingResposeListner extends BaseResposeListner{



    private ProgressDialog myProgressDialog;

    private boolean cancelAble;

    private boolean needShow;

    private NetInterface netInterface;

    public LodingResposeListner(Context context){

        this(false,context,true,true);
    }

    public LodingResposeListner(Fragment v4Fragment){

        this(false,v4Fragment,true,true);
    }

    public LodingResposeListner(Fragment v4Fragment,boolean needShow,boolean cancelAble){
        this(false,v4Fragment.getActivity(),needShow,cancelAble);
    }

    public LodingResposeListner(Context context,boolean needShow, boolean cancelAble){
        this(false,context,needShow,cancelAble);
    }

    public LodingResposeListner(Fragment v4Fragment,boolean needShow){
        this(false,v4Fragment.getActivity(),needShow,true);
    }

    public LodingResposeListner(Context context,boolean needShow){

        this(false,context,needShow,true);
    }







    public LodingResposeListner(boolean tips,Context context){

        this(tips,context,true,true);
    }

    public LodingResposeListner(boolean tips,Fragment v4Fragment){

        this(tips,v4Fragment,true,true);
    }

    public LodingResposeListner(boolean tips,Fragment v4Fragment,boolean needShow,boolean cancelAble){
        this(tips,v4Fragment.getActivity(),needShow,cancelAble);
    }

    public LodingResposeListner(boolean tips,Context context,boolean needShow, boolean cancelAble){
        super(context,tips);
        this.cancelAble = cancelAble;
        this.needShow = needShow;
    }

    public LodingResposeListner(boolean tips,Fragment v4Fragment,boolean needShow){
        this(tips,v4Fragment.getActivity(),needShow,true);
    }

    public LodingResposeListner(boolean tips,Context context,boolean needShow){

        this(tips,context,needShow,true);
    }




    @Override
    public void onStart() {
        super.onStart();
        if(!needShow){
            return;
        }
        myProgressDialog = ProgressDialog.show(context,"","");
        myProgressDialog.setCanceledOnTouchOutside(false);
        myProgressDialog.setCancelable(cancelAble);
        if(cancelAble){
            myProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if(netInterface==null){
                        netInterface = NetInterfaceFactory.getInterface();
                    }
                    cancel(netInterface);
                }
            });
        }

        myProgressDialog.show();
    }

    @Override
    public boolean onSuccess(String result) throws ParseDataException, Exception {
        return super.onSuccess(result);
    }

    @Override
    public void onError(ErrorMessage error) {
        super.onError(error);
    }

    @Override
    public void onEnd(boolean success, ErrorMessage e) {
        super.onEnd(success, e);

        if(myProgressDialog!=null&&myProgressDialog.isShowing()){
            myProgressDialog.dismiss();
            myProgressDialog = null;
        }



    }
}
