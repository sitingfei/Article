package cc.wudoumi.framework.net;



public class NetInterfaceFactory {


	
	public static NetInterface getInterface(){
		NetInterface netInterface = VolleyNet.getInstance();
		return netInterface;
	}


	public static NetInterface getFileUpload() {
		NetInterface netInterface = UploadFileNet.getIntance();
		return netInterface;
	}
	
}
