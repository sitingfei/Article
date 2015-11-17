package cc.wudoumi.framework.interfaces;

import cc.wudoumi.framework.net.DataException;
import cc.wudoumi.framework.net.ErrorMessage;

/**
 * Created by user on 2015/9/14.
 */
public interface SuccessListner<T> {

    T convert(String str) throws DataException;


    boolean onSuccess(T t)throws Exception;

}
