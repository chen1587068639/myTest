package com.example.test.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
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

    /**
     * 发送post请求，访问url，body体参数要求是param1=value1&param2=value2格式
     * CloseableHttpClient.execute方法实现了获取响应包数据，关闭流功能不必手动关闭
     * @param url：请求路径
     * @param headMap：请求头部
     * @param bodyMap：请求体
     * @return
     */
    public static String simPostHttp(String url,Map<String,String> headMap,Map<String,String> bodyMap){
        //创建http客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //CloseableHttpClient通过ResponseHandler把响应包中的数据转化成相应的格式
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpPost httpPost = new HttpPost(url);
        //组装post请求head
        for (Map.Entry<String, String> e : headMap.entrySet()) {
            httpPost.addHeader(e.getKey(), e.getValue());
        }
        //组装post请求head
        List<NameValuePair> pairList = new ArrayList<>();
        if (MapUtils.isNotEmpty(bodyMap)){
            for (Map.Entry<String,String> param : bodyMap.entrySet()){
                pairList.add(new BasicNameValuePair(param.getKey(),param.getValue()));
            }
        }
        try {
            //被UrlEncodedFormEntity编码后的内容传输中变成body的param1=value1&param2=value2格式
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, "UTF-8"));
            return httpclient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            log.info("post请求:{}路径抛出异常:{}",url,e);
        }
        return null;
    }

}
