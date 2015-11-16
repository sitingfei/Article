package cc.wudoumi.framework.net;

/**
 * author: qianjujun on 2015/10/22 12.
 * email:  qianjujun@imcoming.cn
 */
public class AuthException extends DataException{
    public AuthException() {
        super("登陆验证失败");
    }
}
