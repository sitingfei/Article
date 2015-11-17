package cc.wudoumi.framework.interfaces;

import java.util.UUID;

import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.NetInterface;
import cc.wudoumi.framework.net.ParseDataException;

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



    public void onStart(){}



    public void onEnd(boolean success,ErrorMessage e){

    }

    public final void cancel(NetInterface netInterface){
        netInterface.cancel(tag);
    }

    public final String getTag(){
        return tag;
    }
}
