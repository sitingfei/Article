package cc.wudoumi.article.common.response;

import android.content.Context;

import cc.wudoumi.framework.interfaces.RequestListner;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.utils.ToastUtil;

/**
 * author: qianjujun on 2015/11/17 11.
 * email:  qianjujun@imcoming.cn
 */
public class BaseRequestListner extends RequestListner{

    protected Context context;
    private boolean tips;



    public BaseRequestListner(Context context,boolean tips,String tag) {
        super(tag);
        this.context = context;
        this.tips = tips;

    }

    public BaseRequestListner(Context context,boolean tips) {
        this(context,tips,"");
    }


    public BaseRequestListner(Context context) {
        this(context,false,"");
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onEnd(boolean success, ErrorMessage e) {
        if (!success && null != e) {
            if(tips){
                ToastUtil.showToast(context, e.getMessage() + "");
            }
        }
    }
}
