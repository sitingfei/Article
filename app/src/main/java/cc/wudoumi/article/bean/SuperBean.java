package cc.wudoumi.article.bean;

/**
 * author: qianjujun on 2015/11/12 14.
 * email:  qianjujun@imcoming.cn
 */
public class SuperBean<T> extends BaseBean{
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
