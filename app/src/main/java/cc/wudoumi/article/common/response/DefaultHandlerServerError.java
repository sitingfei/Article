package cc.wudoumi.article.common.response;

import cc.wudoumi.framework.net.DataException;

/**
 * author: qianjujun on 2015/11/17 13.
 * email:  qianjujun@imcoming.cn
 */
public class DefaultHandlerServerError implements HandlerServerError{
    @Override
    public DataException handler(int flag, String message) {
        return null;
    }
}
