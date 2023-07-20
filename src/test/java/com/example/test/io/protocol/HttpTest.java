package com.example.test.io.protocol;

import com.alibaba.fastjson.JSONObject;
import com.example.test.util.DateUtils;
import com.example.test.util.HttpUtils;
import com.example.test.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class HttpTest {

    @Test
    void weatherAPI(){

        String http = HttpUtils.getHttp("https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m");
        System.out.println(http);
    }

    @Test
    void httpTest() {
        String url = "https://bike.mofangchuxing.com/operation/share/task/detail?t=Oj9J5BzTP95HEgyzCCPeNThOk";
        String response = HttpUtils.getHttp(url);
        log.info("2222：{}", response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        log.info("entity:{}", jsonObject);
    }

    @Test
    void postHttpTest() {
        String url = "https://bike.mofangchuxing.com/stat/dataStatistics/record";
        Map<String, String> map = new HashMap<>();
        map.put("t", "f2L8MoXcplKvVoB7HPeo3ETfV");
        map.put("startTime", "2022-09-06");
        map.put("value", "1");
        String body = JSONObject.toJSONString(map);
        log.info("body:{}", body);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("Content-Type", "application/json");
        headMap.put("Accept", "application/json");
        String responseBody = HttpUtils.postHttp(url, headMap, body);
        log.info("2222:{}", responseBody);
        JSONObject jsonObject = JSONObject.parseObject(responseBody);
        log.info("json:{}", jsonObject);
    }


    @Test
    void simPostHttpTest() {
        String userName = "zzcx";
        String appKey = "chdi7mnks0n0n9xy07";
        String appSecret = "9l52ho2sd3fv9hweok0ad6qwu0cvuhde";
        String timeStamp = DateUtils.DATE_FORMAT.format(new Date());
        String signTemp = "appKey=" + appKey + "&timeStamp=" + timeStamp + "&userName=" + userName + "&appSecret=" + appSecret;
        String sign = MD5Utils.getMD5(signTemp);
        String iccid = "89860452041970672460";

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("appKey", appKey);
        bodyMap.put("timeStamp", timeStamp);
        bodyMap.put("userName", userName);
        bodyMap.put("sign", sign);
        bodyMap.put("iccid", iccid);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        headMap.put("Accept", "application/json");
        String result = HttpUtils.simPostHttp("http://121.89.194.200:8082/admin/api/v2/cardInfo", headMap, bodyMap);
        System.out.println(result);
    }

    @Test
    void postByJava() {
        String userName = "zzcx";
        String appKey = "chdi7mnks0n0n9xy07";
        String appSecret = "9l52ho2sd3fv9hweok0ad6qwu0cvuhde";
        String timeStamp = DateUtils.DATE_FORMAT.format(new Date());
        String signTemp = "appKey=" + appKey + "&timeStamp=" + timeStamp + "&userName=" + userName + "&appSecret=" + appSecret;
        String sign = MD5Utils.getMD5(signTemp);
        String iccid = "89860490192070729950";
        String param = "appKey=" + appKey;
        param += "&timeStamp=" + timeStamp;
        param += "&userName=" + userName;
        param += "&sign=" + sign;
        param += "&iccid=" + iccid;
        log.info("111:{}", param);
        String result = HttpUtils.sendPost("http://121.89.194.200:8082/admin/api/v2/cardInfo", param);
        System.out.println(result);
    }
    private final static TemplateEngine engine=new TemplateEngine();


    /**
     * 使用 Thymeleaf 渲染 HTML
     * @param template  HTML模板
     * @return  渲染后的HTML
     */
    public static String render(String template){
        Context context = new Context();
        //context.setVariables(params);
        return engine.process(template,context);
    }

    //http://www.tstdoors.com/ldks/43995/4959747_2.html
    @Test
    public void testHttp111(){
        System.out.println("开始");
        String http = HttpUtils.getHttp("http://www.tstdoors.com/ldks/43995/4959747_2.html");
        String render = HttpTest.render(http);
        System.out.println(render);
    }
}
