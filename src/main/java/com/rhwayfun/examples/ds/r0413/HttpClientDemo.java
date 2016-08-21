package com.rhwayfun.examples.ds.r0413;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by rhwayfun on 16-4-13.
 */
public class HttpClientDemo {
    public static void main(String[] args) throws IOException {
        //要请求的协议，在地址前面必须加上http标头，表示这是http请求
        String url = "http://rhwayfun.com";

        //组装http请求
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        //接收响应
        HttpResponse response = client.execute(httpGet);
        //响应体
        HttpEntity entity = response.getEntity();
        //得到响应体的字节表示
        byte[] bytes = EntityUtils.toByteArray(entity);
        //还原成字符串表示
        String res = new String(bytes,"utf-8");
        System.out.println(res);
    }
}
