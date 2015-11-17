package cc.wudoumi.framework.net;


import android.content.Context;

import cc.wudoumi.framework.interfaces.RequestListner;


@Deprecated
public interface NetInterface {

	@Deprecated
	void doRequest(RequestParem requestParem, ResponseListner reponse);
	@Deprecated
	<T>void doRequest(RequestParem requestParem, ResponseListner reponse, SuccessListner<T> successListner);
	void start(Context app);
	void stop();
	void cancel(String tag);

	void cancelContainTag(String tag);



	void doRequest(RequestParem requestParem,RequestListner requestListner);


	<T> void doRequest(RequestParem requestParem,RequestListner requestListner, cc.wudoumi.framework.interfaces.SuccessListner<T> successListner);
}
