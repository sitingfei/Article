package cc.wudoumi.article.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * author: qianjujun on 2015/11/12 14.
 * email:  qianjujun@imcoming.cn
 */
public class ActivityTool {
    public static final <T extends Activity> void turnOther(Context context,Class<T> clazz){
        turnOther(context,clazz,null);
    }


    public static final <T extends Activity> void turnOther(Context context,Class<T> clazz,Bundle bundle){
        Intent intent = new Intent(context,clazz);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
