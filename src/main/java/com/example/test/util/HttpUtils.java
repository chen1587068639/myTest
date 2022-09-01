package com.example.test.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author chengw 2022/8/31
 */
@Slf4j
public class HttpUtils {
    
    public static CloseableHttpResponse getHttp(String url) throws IOException {

        //HttpClient httpClient = HttpClient.New(new URL(url));

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            return response;
        } catch (IOException e) {
            log.info("请求:{}路径抛出异常:{}",url,e);
        }
        return null;
    }
}
