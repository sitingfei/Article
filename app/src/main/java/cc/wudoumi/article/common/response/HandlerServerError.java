package cc.wudoumi.article.common.response;

import cc.wudoumi.framework.net.DataException;

/**
 * author: qianjujun on 2015/11/17 12.
 * email:  qianjujun@imcoming.cn
 */
public interface HandlerServerError {
    DataException handler(int flag,String message);
}
