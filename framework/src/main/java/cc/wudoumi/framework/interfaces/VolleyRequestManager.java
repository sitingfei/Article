package cc.wudoumi.framework.interfaces;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import cc.wudoumi.framework.net.EmptyDataException;
import cc.wudoumi.framework.net.ErrorMessage;
import cc.wudoumi.framework.net.RequestParem;
import cc.wudoumi.framework.utils.NetUtil;

/**
 * author: qianjujun on 2015/11/16 17.
 * email:  qianjujun@imcoming.cn
 */
public class VolleyRequestManager implements RequestManager {

    private static VolleyRequestManager instance = null;
    private RequestQueue requestQueue;
    private Context app;
    private VolleyRequestManager(){
    }



    public static VolleyRequestManager getInstance(){
        if(instance==null){
            synchronized (VolleyRequestManager.class) {
                if(instance==null){
                    instance = new VolleyRequestManager();
                }
            }
        }
        return instance;
    }


    @Override
    public void doRequest(RequestParem requestParem, RequestListner requestListner) {
        doRequest(requestParem,requestListner,null);
    }

    @Override
    public <T> void doRequest(RequestParem requestParem, RequestListner requestListner, SuccessListner<T> successListner) {
        requestListner.onStart();
        if(!NetUtil.isNetworkAvailable(app)){
            ErrorMessage e = new ErrorMessage();
            requestListner.onEnd(false,e);
            return;
        }
        requestQueue.add(new VolleyRequest<T>(requestParem,requestListner,successListner));
    }

    @Override
    public synchronized void start(Application app) {
        if(this.app==null){
            this.app = app;
            requestQueue = Volley.newRequestQueue(app);
        }
    }

    @Override
    public void stop() {
        if(requestQueue!=null){
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {

                @Override
                public boolean apply(Request<?> request) {
                    // TODO Auto-generated method stub
                    handlerCancelRequest(request);
                    return true;
                }
            });

            requestQueue.stop();

            requestQueue=null;
        }

        if(instance!=null){
            instance = null;
        }
    }

    @Override
    public void cancel(final String tag) {
        if(requestQueue!=null){
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    if(tag.equals(request.getTag())){
                        handlerCancelRequest(request);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void cancelContainTag(final String tag) {
        if(requestQueue!=null){
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    if (request.getTag().toString().contains(tag)) {
                        handlerCancelRequest(request);
                        return true;
                    }
                    return false;
                }
            });
        }
    }


    private void handlerCancelRequest(Request<?> request){
        if(request instanceof VolleyRequest){
            ErrorMessage errorMessage = new ErrorMessage(ErrorMessage.TYPE_CANCEL_REQUEST);
            ((VolleyRequest) request).getRequestListner().onEnd(false, errorMessage);
        }
    }


    class VolleyRequest<T> extends Request<String>{

        private RequestListner requestListner;
        private SuccessListner<T> successListner;
        private RequestParem requestParem;

        public VolleyRequest(RequestParem requestParem, final RequestListner requestListner,SuccessListner<T> successListner) {
            super(requestParem.getRequestMethod(), requestParem.getUrl(), new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ErrorMessage errorMessage = new ErrorMessage(error);
                    requestListner.onEnd(false,errorMessage);
                }
            });
            this.requestListner = requestListner;
            this.successListner = successListner;
            this.requestParem = requestParem;

            setTag(requestListner.getTag());
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }

        @Override
        protected  void deliverResponse(String response) {
            Log.d("RequestManager","------------------------------------------------------------------");
            //Log.d("RequestManager","result:"+response);
            Log.d("RequestManager","------------------------------------------------------------------");
            boolean result = false;
            ErrorMessage errorMessage = null;
            if(successListner!=null){
                try {
                    T t = successListner.convert(response);

                    if(t==null){
                        throw new EmptyDataException();
                    }

                    if(t instanceof List){
                        if(((List) t).size()==0){
                            throw new EmptyDataException();
                        }
                    }

                    result = successListner.onSuccess(t);
                    if(!result){
                        errorMessage = new ErrorMessage(ErrorMessage.TYPE_NO_DATA);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    errorMessage = new ErrorMessage(e);
                }finally {
                    requestListner.onEnd(result,errorMessage);
                }
            }else{
                requestListner.onEnd(true,errorMessage);
            }

        }

        public RequestListner getRequestListner() {
            return requestListner;
        }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Log.d("RequestManager","------------------------------------------------------------------");
            Log.d("RequestManager","requestParems:"+requestParem);
            Log.d("RequestManager","------------------------------------------------------------------");
            return requestParem;
        }
    }
}
