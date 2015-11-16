package cc.wudoumi.framework.net;

import android.app.Application;
import android.content.Context;
import android.os.Handler;


/**
 * author: qianjujun on 2015/10/26 16.
 * email:  qianjujun@imcoming.cn
 */
public class UploadFileNet implements NetInterface{

    private static UploadFileNet uploadFileNet;
    private UploadFileNet(){

    }
    public static UploadFileNet getIntance(){
        if(uploadFileNet==null){
            synchronized (UploadFileNet.class){
                uploadFileNet = new UploadFileNet();
            }
        }
        return uploadFileNet;
    }

    private Handler mHandler = new Handler();
    boolean success = false;
    ErrorMessage errorMessage = null;
    @Override
    public void doRequest(final RequestParem requestParem, final ResponseListner reponse) {
        reponse.onStart();
        success = false;
        errorMessage = null;
        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    HttpPostUtil httpPostUtil = new HttpPostUtil(requestParem);
                    byte[] data = httpPostUtil.send();
                    final String result = new String(data);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                success = reponse.onSuccess(result);
                            } catch (Exception e) {
                                e.printStackTrace();
                                errorMessage = new ErrorMessage(e);
                                reponse.onError(errorMessage);
                            }

                            reponse.onEnd(success,errorMessage);
                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            errorMessage = new ErrorMessage(e);
                            reponse.onError(errorMessage);
                            reponse.onEnd(false, errorMessage);
                        }
                    });

                }
            }
        }.start();

    }

    @Override
    public <T> void doRequest(RequestParem requestParem, ResponseListner reponse, SuccessListner<T> successListner) {

    }

    @Override
    public void start(Context app) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void cancel(String tag) {

    }

    @Override
    public void cancelContainTag(String tag) {

    }
}
