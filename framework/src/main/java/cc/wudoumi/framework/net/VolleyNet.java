package cc.wudoumi.framework.net;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;
import java.util.Map;

import cc.wudoumi.framework.utils.NetUtil;


public class VolleyNet implements NetInterface {

	private static VolleyNet instance = null;
	private RequestQueue requestQueue;
	private VolleyNet(){

	}


	private Context app;
	public static VolleyNet getInstance(){
		if(instance==null){
			synchronized (VolleyNet.class) {
				if(instance==null){
					instance = new VolleyNet();
				}
			}
		}
		return instance;
	}


	@Override
	public void doRequest(RequestParem requestParem, ResponseListner reponse) {
		reponse.onStart();
		if(!NetUtil.isNetworkAvailable(app)){
			ErrorMessage e = new ErrorMessage();
			reponse.onError(e);
			reponse.onEnd(false,e);
			return;
		}
		requestQueue.add(new AnlaiyeRequest(requestParem,reponse));
	}

	@Override
	public <T> void doRequest(RequestParem requestParem, ResponseListner reponse, SuccessListner<T> successListner) {
		reponse.onStart();
		if(!NetUtil.isNetworkAvailable(app)){
			ErrorMessage e = new ErrorMessage();
			reponse.onError(e);
			reponse.onEnd(false,e);
			return;
		}
		requestQueue.add(new AnlaiyeRequest(requestParem, reponse,successListner));
	}

	@Override
	public synchronized void start(Context app) {
		if(this.app==null){
			this.app = app;
			requestQueue = Volley.newRequestQueue(app);
		}
	}


	class AnlaiyeRequest extends StringRequest {

		private RequestParem requestParem;

		private ResponseListner responseListner;

		public AnlaiyeRequest(RequestParem requestParem, final ResponseListner reponse) {
			this(requestParem,reponse,null);
		}


		public <T> AnlaiyeRequest(final RequestParem requestParem, final ResponseListner reponse, final SuccessListner<T> successListner) {
			super(requestParem.getRequestMethod(), requestParem.getUrl(), new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					if(response!=null&&response.length()>0){
						response = response.replace("null", "\"\"");
						if(response.charAt(0) == 65279){
							response = response.substring(1, response.length());
						}
					}
					android.util.Log.d("---->AnlaiyeResult", requestParem.getUrl() + " ---> result : " + response);
					boolean success = false;
					ErrorMessage errorMessage = null;
					if(successListner==null){
						try {
							success = reponse.onSuccess(response);
							if(!success){
								errorMessage = new ErrorMessage(ErrorMessage.TYPE_NO_DATA);
							}
						} catch (Exception e){
							e.printStackTrace();
							errorMessage = new ErrorMessage(e);
							reponse.onError(errorMessage);
						}finally {
							reponse.onEnd(success,errorMessage);
						}


					}else{
						try {
							T t = successListner.convert(response);
							if(t!=null){

								if(t instanceof List && ((List) t).size()==0){
									errorMessage = new ErrorMessage(ErrorMessage.TYPE_NO_DATA);
									successListner.onFail(errorMessage);
								}else{
									success = successListner.onSuccess(t);
									if(!success){
										errorMessage = new ErrorMessage(ErrorMessage.TYPE_NO_DATA);
									}
								}
							}else{
								errorMessage = new ErrorMessage(ErrorMessage.TYPE_DATA_PARSE);
								successListner.onFail(errorMessage);
							}

						} catch (Exception e) {
							e.printStackTrace();
							errorMessage = new ErrorMessage(e);
							successListner.onFail(errorMessage);

						}finally {
							reponse.onEnd(success,errorMessage);
						}
					}


				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					ErrorMessage em = new ErrorMessage(error);
					reponse.onError(em);
					reponse.onEnd(false,em);
				}
			});
			setTag(reponse.getTag());
			this.requestParem = requestParem;
			this.responseListner = reponse;
		}


		@Override
		protected Map<String, String> getParams() throws AuthFailureError {
			Log.d("---->AnlaiyeResult","parems:"+requestParem);
			return requestParem;
		}

		public ResponseListner getResponseListner() {
			return responseListner;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		if(requestQueue!=null){
			requestQueue.cancelAll(new RequestQueue.RequestFilter() {
				
				@Override
				public boolean apply(Request<?> request) {
					// TODO Auto-generated method stub
					if(request instanceof AnlaiyeRequest){
						ErrorMessage errorMessage = new ErrorMessage(ErrorMessage.TYPE_CANCEL_REQUEST);
						((AnlaiyeRequest) request).getResponseListner().onError(errorMessage);
						((AnlaiyeRequest) request).getResponseListner().onEnd(false,errorMessage);
					}
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
						if(request instanceof AnlaiyeRequest){
							ErrorMessage errorMessage = new ErrorMessage(ErrorMessage.TYPE_CANCEL_REQUEST);
							((AnlaiyeRequest) request).getResponseListner().onError(errorMessage);
							((AnlaiyeRequest) request).getResponseListner().onEnd(false, errorMessage);
						}
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
					if(request.getTag().toString().contains(tag)){
						if(request instanceof AnlaiyeRequest){
							ErrorMessage errorMessage = new ErrorMessage(ErrorMessage.TYPE_CANCEL_REQUEST);
							((AnlaiyeRequest) request).getResponseListner().onError(errorMessage);
							((AnlaiyeRequest) request).getResponseListner().onEnd(false, errorMessage);
						}
						return true;
					}
					return false;
				}
			});
		}
	}

}
