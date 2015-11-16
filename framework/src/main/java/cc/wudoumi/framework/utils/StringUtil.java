package cc.wudoumi.framework.utils;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class StringUtil {
    private StringUtil(){

    }




    public static boolean isEmpty(String string){
        return string==null||string.trim().length()==0;
    }

    public static boolean isNull(String string){

        return isEmpty(string)||"null".equals(string.toLowerCase());
    }
}
