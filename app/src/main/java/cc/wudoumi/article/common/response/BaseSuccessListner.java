package cc.wudoumi.article.common.response;

import cc.wudoumi.framework.interfaces.SuccessListner;
import cc.wudoumi.framework.net.DataException;

/**
 * author: qianjujun on 2015/11/17 12.
 * email:  qianjujun@imcoming.cn
 */
public abstract class BaseSuccessListner<T> implements SuccessListner<T>{

    protected HandlerServerError handlerServerError;


    public BaseSuccessListner(){
        handlerServerError = new DefaultHandlerServerError();
    }

    @Override
    public T convert(String str) throws DataException {
        return null;
    }

    @Override
    public boolean onSuccess(T t) throws Exception {
        return false;
    }


    public void setHandlerServerError(HandlerServerError handlerServerError) {
        this.handlerServerError = handlerServerError;
    }
}
