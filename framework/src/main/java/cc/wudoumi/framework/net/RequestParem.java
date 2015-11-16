package cc.wudoumi.framework.net;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by user on 2015/9/10.
 */
public class RequestParem extends HashMap<String,String> {

    public static final int GET = 0;

    public static final int POST =1;

    private int requestMethod;
    private final String url;


    public RequestParem(String url){
        this(url,POST);
    }

    public RequestParem(String url,int requestMethod){
        this.requestMethod = requestMethod;
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public int getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(int requestMethod) {
        this.requestMethod = requestMethod;
    }




    private HashMap<String,File> files = new HashMap<>();


    public void putFile(String key,File file){
        files.put(key,file);
    }

    Set<String> fileKeySet(){
        return files.keySet();
    }

    File getFileByName(String name){
        if(files.containsKey(name)){
            return files.get(name);
        }
        return null;
    }

    @Override
    public String toString() {
        return "RequestParem{" +
                "url='" + url + '\'' +
                '}' + super.toString();
    }
}
