package cc.wudoumi.article.common.response;

import android.content.Context;
import android.text.TextUtils;

import org.json.JSONObject;

import cc.wudoumi.framework.net.AuthException;
import cc.wudoumi.framework.net.DataException;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.ParseDataException;
import cc.wudoumi.framework.net.ResponseListner;
import cc.wudoumi.framework.utils.ToastUtil;


/**
 * author: qianjujun on 2015/10/22 12.
 * email:  qianjujun@imcoming.cn
 *
 * 网络接口回调
 *
 * 默认实现了出现错误时的Toast处理，登陆验证失败后重新登录的处理
 * 实现服务器返回值基本的业务处理
 */
public class BaseResposeListner extends ResponseListner {

    private boolean isTips;

    protected final Context context;

    public BaseResposeListner(Context context) {

        this(context,false);
    }

    public BaseResposeListner(Context context,boolean isTips) {
        this.context = context;
        this.isTips = isTips;
    }


    @Override
    public void onEnd(boolean success, ErrorMessage e) {
        super.onEnd(success, e);

        if (!success && null != e) {

            if(e.getErrorType() == ErrorMessage.TYPE_LOSE_TOKEN){

            }

            if(isTips){
                ToastUtil.showToast(context,e.getMessage()+"");
            }

        }
    }


    @Override
    public boolean onSuccess(String result) throws Exception {
        try {
            JSONObject jsonObject = new JSONObject(result);
            String flag = jsonObject.getString("flag");
            if ("1".equals(flag)) {
                return true;
            } else if("-633".equals(flag)){
                throw new AuthException();
            }else {
                String message = jsonObject.getString("message");
                if (TextUtils.isEmpty(message)) {
                    message = "服务器数据错误";
                }

                throw new DataException(message,flag);
            }
        } catch (DataException e) {//服务端提示
            throw e;
        } catch (Exception e) {//数据解析错误
            e.printStackTrace();
            throw new ParseDataException();
        }

    }
}
