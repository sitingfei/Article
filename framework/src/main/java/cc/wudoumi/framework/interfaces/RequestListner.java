package cc.wudoumi.framework.interfaces;

import java.util.UUID;

import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.utils.NetUtil;

/**
 * author: qianjujun on 2015/11/16 17.
 * email:  qianjujun@imcoming.cn
 */
public abstract class RequestListner {
    private final String tag;


    public RequestListner(){
        tag = UUID.randomUUID().toString();
    }


    public RequestListner(String tag){
        this.tag = tag + UUID.randomUUID().toString();
    }



    public abstract void onStart();



    public abstract void onEnd(boolean success,ErrorMessage e);

    public final void cancel(){
        //NetUtil.getRequestManager().cancel(tag);
        if(cancelRequest!=null){
            cancelRequest = new SimpleCancelRequest();
        }

        cancelRequest.cancel();
    }

    public final String getTag(){
        return tag;
    }



    public interface CancelRequestI{
        void cancel();
    }


    private CancelRequestI cancelRequest;

    public void setCancelRequest(CancelRequestI cancelRequest) {
        this.cancelRequest = cancelRequest;
    }


    class SimpleCancelRequest implements CancelRequestI{

        @Override
        public void cancel() {
            NetUtil.getRequestManager().cancel(getTag());
        }
    }
}
