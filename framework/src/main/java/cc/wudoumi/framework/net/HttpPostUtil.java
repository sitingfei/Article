package cc.wudoumi.framework.net;

/**
 * Created by qianjujun on 2015/7/21 0021 12:53.
 * qianjujun@163.com
 */

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;


public class HttpPostUtil {
    URL url;
    HttpURLConnection conn;
    String boundary = "aifudao7816510d1hq";

    RequestParem requestParem;
    DataOutputStream ds;



    public HttpPostUtil(RequestParem requestParem) throws MalformedURLException {
        this.requestParem = requestParem;
        this.url = new URL(requestParem.getUrl());
    }


    // 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
    public byte[] send() throws IOException {
        initConnection();
        conn.connect();
        ds = new DataOutputStream(conn.getOutputStream());
        writeStringParams();
        writeFileParams();

        paramsEnd();
        InputStream in = conn.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
        conn.disconnect();
        return out.toByteArray();
    }

    //文件上传的connection的一些必须设置
    private void initConnection() throws IOException {
        conn = (HttpURLConnection) this.url.openConnection();
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setChunkedStreamingMode(0);
        conn.setConnectTimeout(10000); //连接超时为10秒
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
    }

    //普通字符串数据
    private void writeStringParams() throws IOException {
        Set<String> keySet = requestParem.keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String name = it.next();
            String value = requestParem.get(name);

            ds.writeBytes("--" + boundary + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name
                    + "\"\r\n");
            ds.writeBytes("\r\n");
            ds.writeBytes(value + "\r\n");
        }
    }


    //文件数据
    private void writeFileParams() throws IOException {
        Set<String> keySet = requestParem.fileKeySet();
        byte[] buf ;
        for (Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
            String name = it.next();
            File value = requestParem.getFileByName(name);
            ds.writeBytes("--" + boundary + "\r\n");
            ds.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + value.getName() + "\"\r\n");
            ds.writeBytes("Content-Type: " + getContentType(value) + "\r\n");
            ds.writeBytes("\r\n");
//            RandomAccessFile raf = new RandomAccessFile(value, "rw");
//            buf = new byte[1024];
//            int offset ;
//            while((offset = raf.read(buf))!=-1){
//                ds.write(buf, 0, offset);
//            }

            ds.write(getBytes(value));
            ds.writeBytes("\r\n");


        }
    }

    //获取文件的上传类型，图片格式为image/png,image/jpg等。非图片为application/octet-stream
    private String getContentType(File f) {

        return "application/octet-stream";  // 此行不再细分是否为图片，全部作为application/octet-stream 类型


    }

    //把文件转换成字节数组
    private byte[] getBytes(File f) throws IOException {
        FileInputStream in = new FileInputStream(f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            out.write(b, 0, n);
        }
        in.close();

        return out.toByteArray();
    }

    //添加结尾数据
    private void paramsEnd() throws IOException {
        ds.writeBytes("--" + boundary + "--" + "\r\n");
        ds.writeBytes("\r\n");


    }




}

