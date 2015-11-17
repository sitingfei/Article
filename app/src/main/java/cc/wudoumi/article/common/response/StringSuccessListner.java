package cc.wudoumi.article.common.response;


import org.json.JSONObject;

import cc.wudoumi.framework.net.DataException;
import cc.wudoumi.framework.net.ParseDataException;

/**
 * author: qianjujun on 2015/11/17 12.
 * email:  qianjujun@imcoming.cn
 */
public class StringSuccessListner extends BaseSuccessListner<String> {
    @Override
    public String convert(String str) throws DataException {
        try {
            JSONObject jsonObject = new JSONObject(str);
            int flag = jsonObject.getInt("flag");
            switch (flag){
                case 1:
                    return str;
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

    @Override
    public boolean onSuccess(String s) throws Exception {
        return false;
    }
}
