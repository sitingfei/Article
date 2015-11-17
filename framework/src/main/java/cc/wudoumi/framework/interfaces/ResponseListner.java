package cc.wudoumi.framework.interfaces;

/**
 * author: qianjujun on 2015/11/17 16.
 * email:  qianjujun@imcoming.cn
 */
public interface ResponseListner<T> {
    boolean onSuccess(T t)throws Exception;
}
