package com.example.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.test.util.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
@Data
class DemoData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
@SpringBootTest
@Slf4j
class TestApplicationTests {
    @Test
    void contextLoads(){
        //通过SFTP获取远端服务器文件
        List<String> file = SftpUtils.readFile("root", "mfcxgxddc(@zhongzhong)", 22, "8.142.4.4", "/data/jar-server/admin/api/application-prod.yml");
        System.out.println(file);
    }

    @Test
    void httpTest(){
        String url = "https://bike.mofangchuxing.com/operation/share/task/detail?t=Oj9J5BzTP95HEgyzCCPeNThOk";
        String response = HttpUtils.getHttp(url);
        log.info("2222：{}",response);
        JSONObject jsonObject = JSONObject.parseObject(response);
        log.info("entity:{}",jsonObject);
    }

    @Test
    void postHttpTest(){
        String url = "https://bike.mofangchuxing.com/stat/dataStatistics/record";
        Map<String,String> map = new HashMap<>();
        map.put("t","f2L8MoXcplKvVoB7HPeo3ETfV");
        map.put("startTime","2022-09-06");
        map.put("value","1");
        String body = JSONObject.toJSONString(map);
        log.info("body:{}",body);
        Map<String, String> headMap = new HashMap<>();
        headMap.put("Content-Type","application/json");
        headMap.put("Accept","application/json");
        String responseBody = HttpUtils.postHttp(url,headMap,body);
        log.info("2222:{}",responseBody);
        JSONObject jsonObject =  JSONObject.parseObject(responseBody);
        log.info("json:{}",jsonObject);
    }


    @Test
    void simPostHttpTest() {
        String userName = "zzcx";
        String appKey = "chdi7mnks0n0n9xy07";
        String appSecret = "9l52ho2sd3fv9hweok0ad6qwu0cvuhde";
        String timeStamp = DateUtils.DATE_FORMAT.format(new Date());
        String signTemp = "appKey=" + appKey + "&timeStamp=" + timeStamp +"&userName=" + userName + "&appSecret=" + appSecret;
        String sign = MD5Utils.getMD5(signTemp);
        String iccid = "89860452041970672460";

        Map<String,String> bodyMap = new HashMap<>();
        bodyMap.put("appKey",appKey);
        bodyMap.put("timeStamp",timeStamp);
        bodyMap.put("userName",userName);
        bodyMap.put("sign",sign);
        bodyMap.put("iccid",iccid);
        Map<String,String> headMap = new HashMap<>();
        headMap.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        headMap.put("Accept","application/json");
        String result = HttpUtils.simPostHttp("http://121.89.194.200:8082/admin/api/v2/cardInfo",headMap,bodyMap);
        System.out.println(result);
    }

    @Test
    void postByJava() {
        String userName = "zzcx";
        String appKey = "chdi7mnks0n0n9xy07";
        String appSecret = "9l52ho2sd3fv9hweok0ad6qwu0cvuhde";
        String timeStamp = DateUtils.DATE_FORMAT.format(new Date());
        String signTemp = "appKey=" + appKey + "&timeStamp=" + timeStamp +"&userName=" + userName + "&appSecret=" + appSecret;
        String sign = MD5Utils.getMD5(signTemp);
        String iccid = "89860490192070729950";
        String param = "appKey=" + appKey;
        param += "&timeStamp=" + timeStamp;
        param += "&userName=" + userName;
        param += "&sign=" + sign;
        param += "&iccid=" + iccid;
        log.info("111:{}",param);
        String result = HttpUtils.sendPost("http://121.89.194.200:8082/admin/api/v2/cardInfo",param);
        System.out.println(result);
    }

}
