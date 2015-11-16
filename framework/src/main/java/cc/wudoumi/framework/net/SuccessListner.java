package cc.wudoumi.framework.net;

/**
 * Created by user on 2015/9/14.
 */
public interface SuccessListner<T> {

    T convert(String str) throws DataException;

    boolean onSuccess(T t)throws Exception;


    void onFail(ErrorMessage ne);
}
