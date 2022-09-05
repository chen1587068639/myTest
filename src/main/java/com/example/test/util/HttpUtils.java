package com.example.test.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chengw 2022/8/31
 */
@Slf4j
public class HttpUtils {

    /**
     * get请求：传输url，返回
     * @param url
     * @return
     * @throws IOException
     */
    public static String getHttp(String url){
        //创建htpp客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        //CloseableHttpResponse response = null;
        try {
            //发送get请求，return response body
            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            return httpclient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            log.info("get请求:{}路径抛出异常:{}",url,e);
        }
        return null;
    }


    public static String postHttp(String url,String jsonBody){
        //创建http客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler=new BasicResponseHandler();
        HttpPost httpPost = new HttpPost(url);

        try {

            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.setHeader("Accept", "application/json");
            StringEntity stringEntity = new StringEntity(jsonBody, "UTF-8"); // or "gbk"
            httpPost.setEntity(stringEntity);
            return httpclient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            log.info("post请求:{}路径抛出异常:{}",url,e);
        }
        return null;
    }



    /**
     * post form
     *
     * @param headers
     * @param bodys
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> headers,
                                      Map<String, String> bodys)
            throws Exception {
        //创建http客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, String> e : headers.entrySet()) {
            httpPost.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");

            httpPost.setEntity(formEntity);
        }
        ResponseHandler<String> responseHandler=new BasicResponseHandler();

        return httpClient.execute(httpPost,responseHandler);
    }

    /**
     * map转json
     * @param map
     * @return
     */
    public static String getJSONString(Map<String,Object> map){
        return JSONObject.toJSONString(map);
    }
}
