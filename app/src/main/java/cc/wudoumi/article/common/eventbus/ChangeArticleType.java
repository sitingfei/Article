package cc.wudoumi.article.common.eventbus;

/**
 * author: qianjujun on 2015/11/12 17.
 * email:  qianjujun@imcoming.cn
 */
public class ChangeArticleType {
    private int parentTypeId;

    public int getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(int parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public ChangeArticleType(int parentTypeId) {
        this.parentTypeId = parentTypeId;
    }
}
