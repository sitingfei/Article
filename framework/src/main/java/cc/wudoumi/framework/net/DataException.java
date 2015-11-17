package cc.wudoumi.framework.net;

/**
 * Created by user_2 on 2015/9/17.
 */
public class DataException extends Exception {

    private int code;

    public DataException(String detailMessage) {
        super(detailMessage);
    }

    public DataException(Throwable throwable) {
        super(throwable);
    }

    public DataException(String detailMessage,int code) {
        super(detailMessage);
        this.code = code;
    }

    public DataException(Throwable throwable,int code) {
        super(throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
