package cc.wudoumi.article.bean;

/**
 * author: qianjujun on 2015/11/12 14.
 * email:  qianjujun@imcoming.cn
 */
public class BaseBean {
    private int flag;

    private boolean success;

    private String message;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
