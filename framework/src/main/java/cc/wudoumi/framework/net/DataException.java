package cc.wudoumi.framework.net;

/**
 * Created by user_2 on 2015/9/17.
 */
public class DataException extends Exception {

    private String code;

    public DataException(String detailMessage) {
        super(detailMessage);
    }

    public DataException(Throwable throwable) {
        super(throwable);
    }

    public DataException(String detailMessage,String code) {
        super(detailMessage);
        this.code = code;
    }

    public DataException(Throwable throwable,String code) {
        super(throwable);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
