package cc.wudoumi.article.common.response;


import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONObject;

import cc.wudoumi.framework.net.AuthException;
import cc.wudoumi.framework.net.DataException;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.ParseDataException;
import cc.wudoumi.framework.net.SuccessListner;


/**
 * Created by user on 2015/9/14.
 *
 *
 */
public abstract class GsonSuccessListner<T> implements SuccessListner<T> {
    private static Gson gson = new Gson();
    private final Class<T> clazz;

    public GsonSuccessListner(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convert(String str) throws DataException {
        if (TextUtils.isEmpty(str)) {
            throw new ParseDataException();
        }
        try {
            JSONObject jsonObject = new JSONObject(str);
            String flag = jsonObject.getString("flag");
            if ("1".equals(flag)) {
                T t = gson.fromJson(jsonObject.getJSONObject("data").toString(), clazz);
                return t;
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

    @Override
    public void onFail(ErrorMessage ne) {

    }
}
