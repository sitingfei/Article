package cc.wudoumi.framework.net;



import java.util.UUID;

@Deprecated
public abstract class ResponseListner {
	private final String tag;


	public ResponseListner(){
		tag = UUID.randomUUID().toString();
	}


	public ResponseListner(String tag){
		this.tag = tag + UUID.randomUUID().toString();
	}



	public void onStart(){}

	public boolean onSuccess(String result)throws ParseDataException,Exception {
		return false;
	}

	public void onError(ErrorMessage error){}

	public void onEnd(boolean success,ErrorMessage e){

	}
	
	public final void cancel(NetInterface netInterface){
		netInterface.cancel(tag);
	}

	public final String getTag(){
		return tag;
	}







}
