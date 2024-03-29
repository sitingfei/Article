package cc.wudoumi.article.common.response;


import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONObject;

import cc.wudoumi.framework.net.DataException;
import cc.wudoumi.framework.net.ParseDataException;


/**
 * Created by user on 2015/9/14.
 *
 *
 */
public abstract class GsonSuccessListner<T> extends BaseSuccessListner<T> {
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
            int flag = jsonObject.getInt("flag");

            switch (flag){
                case 1:
                    T t = gson.fromJson(jsonObject.getJSONObject("data").toString(), clazz);
                    return t;
                default:
                    String message = jsonObject.getString("message");
                    throw  handlerServerError.handler(flag,message);
            }

        } catch (DataException e) {//服务端提示
            throw e;
        } catch (Exception e) {//数据解析错误
            e.printStackTrace();
            throw new ParseDataException();
        }

    }


}
