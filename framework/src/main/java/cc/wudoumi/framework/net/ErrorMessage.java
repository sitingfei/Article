package cc.wudoumi.framework.net;

import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

/**
 * author: qianjujun on 2015/9/23 12.
 * email:  qianjujun@imcoming.cn
 */
public class ErrorMessage {
    public static final int TYPE_SERVER = 1;//服务器相关
    public static final int TYPE_DATA_PARSE = 2;//数据解析异常
    public static final int TYPE_UI = 3;//客户端异常
    public static final int TYPE_NO_NET = 4;//无网络
    public static final int TYPE_NO_DATA = 5;//无数据


    public static final int TYPE_LOSE_TOKEN = 6;//登陆验证

    public static final int TYPE_CANCEL_REQUEST = 7;//取消请求




    private int errorType;
    private String message;//展示给客户的信息

    private String detaileMessage;// 真实错误信息，异常里的detail

    private String errorFlag;


    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDetaileMessage() {
        return detaileMessage;
    }

    public void setDetaileMessage(String detaileMessage) {
        this.detaileMessage = detaileMessage;
    }


    public String getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(String errorFlag) {
        this.errorFlag = errorFlag;
    }

    public ErrorMessage(Throwable e){
        if(e==null){
            errorType = TYPE_NO_NET;
            message = "未知错误";
            detaileMessage = "未知错误";
            return;
        }

        detaileMessage = e.getMessage();
        if(e instanceof TimeoutError){
            message = "访问服务器超时";
            errorType = TYPE_SERVER;
        }else if (e instanceof VolleyError){
            message = "访问服务器出错";
            errorType = TYPE_SERVER;
        }else if(e instanceof AuthException){//解析数据
            message = e.getMessage();
            errorType = TYPE_LOSE_TOKEN;
        }else if(e instanceof EmptyDataException){//空数据
            message = e.getMessage();
            errorType = TYPE_NO_DATA;
        }else if(e instanceof DataException){
            message = e.getMessage();
            errorType = TYPE_DATA_PARSE;
            errorFlag = ((DataException)e).getCode();
        }else{
            //此异常属于bug，必须要修复
            errorType = TYPE_UI;
            message = "数据处理异常";
        }
    }

    public ErrorMessage(){
        errorType = TYPE_NO_NET;
        detaileMessage = "无网络";
        message = "无网络";
    }

    public ErrorMessage(int type,String message){
        this.errorType = type;
        this.message = message;
        this.detaileMessage = message;

    }


    public ErrorMessage(int type){
        this.errorType = type;

        switch (errorType){
            case TYPE_SERVER:
                setMessages("访问服务器出错");
                break;
            case TYPE_DATA_PARSE:
                setMessages("服务器数据错误");
                break;
            case TYPE_NO_DATA:
                setMessages("无更多数据");
                break;
            case TYPE_NO_NET:
                setMessages("无网络");
                break;
            case TYPE_UI:
                setMessages("数据处理异常");
                break;
            case TYPE_CANCEL_REQUEST:
                setMessage("已取消请求");
                break;
            default:
                break;

        }
    }


    private void setMessages(String message){
        setDetaileMessage(message);
        setMessage(message);
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "errorType=" + errorType +
                ", message='" + message + '\'' +
                ", detaileMessage='" + detaileMessage + '\'' +
                ", errorFlag='" + errorFlag + '\'' +
                '}';
    }



}
