package cc.wudoumi.framework.interfaces;


import android.app.Application;

import cc.wudoumi.framework.net.RequestParem;

/**
 * author: qianjujun on 2015/11/16 17.
 * email:  qianjujun@imcoming.cn
 */
public interface RequestManager {
    void doRequest(RequestParem requestParem,RequestListner requestListner);


    <T> void doRequest(RequestParem requestParem,RequestListner requestListner,SuccessListner<T> successListner);


    void start(Application app);

    void stop();

    void cancel(String tag);

    void cancelContainTag(String tag);
}
