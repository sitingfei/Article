package cc.wudoumi.framework.net;


import android.content.Context;

public interface NetInterface {

	void doRequest(RequestParem requestParem, ResponseListner reponse);
	<T>void doRequest(RequestParem requestParem, ResponseListner reponse, SuccessListner<T> successListner);
	void start(Context app);
	void stop();
	void cancel(String tag);

	void cancelContainTag(String tag);
}
