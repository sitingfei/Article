package cc.wudoumi.framework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * author: qianjujun on 2015/9/23 13.
 * email:  qianjujun@imcoming.cn
 */
public class NetUtil {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (cm == null) {
//        } else {
//            //如果仅仅是用来判断网络连接
//            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
//            NetworkInfo[] info = cm.getAllNetworkInfo();
//            if (info != null) {
//                for (int i = 0; i < info.length; i++) {
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
        NetworkInfo mNetworkInfo = cm
                .getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
       /* if(cm!=null){
            return cm.getActiveNetworkInfo().isAvailable();
        }*/
        return false;
    }
}
