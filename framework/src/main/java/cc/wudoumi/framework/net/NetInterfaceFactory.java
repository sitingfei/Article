package cc.wudoumi.framework.net;


@Deprecated
public class NetInterfaceFactory {


	@Deprecated
	public static NetInterface getInterface(){
		NetInterface netInterface = VolleyNet.getInstance();
		return netInterface;
	}

	@Deprecated
	public static NetInterface getFileUpload() {
		NetInterface netInterface = UploadFileNet.getIntance();
		return netInterface;
	}




	
}
