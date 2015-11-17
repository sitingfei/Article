package cc.wudoumi.framework.net;

/**
 * author: qianjujun on 2015/11/17 10.
 * email:  qianjujun@imcoming.cn
 */
public class EmptyDataException extends DataException {
    public EmptyDataException() {
        super("没有更多数据");
    }
}
