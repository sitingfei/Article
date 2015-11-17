package cc.wudoumi.framework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cc.wudoumi.framework.interfaces.RequestManager;
import cc.wudoumi.framework.interfaces.VolleyRequestManager;


/**
 * author: qianjujun on 2015/9/23 13.
 * email:  qianjujun@imcoming.cn
 */
public class NetUtil {

    /**
     * 是否有网络
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm!=null){
            NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    public static RequestManager getRequestManager(){
        return VolleyRequestManager.getInstance();
    }



}
