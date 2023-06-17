package com.example.io.file;

import com.example.test.util.FileUtils;
import com.example.test.util.file.ExcelUtils;
import com.example.test.util.file.TestHead;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.*;

/**
 * @Author: chengw
 * @Date: 2022/12/28 上午11:15
 */
@Slf4j
@SpringBootTest
public class FileReadTest {
//
//    @Test
//    public void testCSVRead(){
//        File file = new File("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
//        List<List<String>> objects = ExcelUtils.readCSV(file);
//        List<String> orderList = new ArrayList<>();
//        objects.forEach(c -> {
//            orderList.add(c.get(5));
//        });
//        System.out.println(orderList);
//    }

    @Test
    public void testCSVRead222(){
        File file = new File("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
        List<List<String>> head = new ArrayList<>();
        List<String> list = Arrays.asList("交易时间","公众账号ID","商户号","特约商户号","设备号","微信订单号","商户订单号","用户标识","交易类型",	"交易状态","付款银行","货币种类","应结订单金额","代金券金额","微信退款单号","商户退款单号","退款金额",	"充值券退款金额",	"退款类型","退款状态","商品名称","商户数据包","手续费","费率","订单金额","申请退款金额","费率备注");
        list.forEach(c -> head.add(Collections.singletonList(c)));
        List<LinkedHashMap<String,String>> objects = ExcelUtils.readCSV(head,file);
        List<List<String>> resultList = new ArrayList<>();
        objects.forEach(c -> resultList.add((List<String>)c.values()));
        LinkedHashMap<String,String> strings = objects.get(0);
        System.out.println(strings.get(5));
        System.out.println(resultList);
    }

    @Test
    public void testCSVRead333(){
        File file = new File("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
        List<List<String>> head = new ArrayList<>();
        List<String> list = Arrays.asList("交易时间","公众账号ID","商户号","特约商户号","设备号","微信订单号","商户订单号","用户标识","交易类型",	"交易状态","付款银行","货币种类","应结订单金额","代金券金额","微信退款单号","商户退款单号","退款金额",	"充值券退款金额",	"退款类型","退款状态","商品名称","商户数据包","手续费","费率","订单金额","申请退款金额","费率备注");
        list.forEach(c -> head.add(Collections.singletonList(c)));
        List<String> strings = ExcelUtils.readCSVTest(head, file, 0);
        System.out.println(strings);
    }

    @Test
    public void testCSVRead444(){
        File file = new File("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
        List<List<String>> head = new ArrayList<>();
        List<String> list = Arrays.asList("交易时间","公众账号ID","商户号","特约商户号","设备号","微信订单号","商户订单号","用户标识","交易类型",	"交易状态","付款银行","货币种类","应结订单金额","代金券金额","微信退款单号","商户退款单号","退款金额",	"充值券退款金额",	"退款类型","退款状态","商品名称","商户数据包","手续费","费率","订单金额","申请退款金额","费率备注");
        list.forEach(c -> head.add(Collections.singletonList(c)));
        List<TestHead> strings = ExcelUtils.readRd("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
        System.out.println(strings);
    }

    @Test
    public void testCSVRead555(){
        File file = new File("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
        List<List<String>> head = new ArrayList<>();
        List<String> list = Arrays.asList("交易时间","公众账号ID","商户号","特约商户号","设备号","微信订单号","商户订单号","用户标识","交易类型",	"交易状态","付款银行","货币种类","应结订单金额","代金券金额","微信退款单号","商户退款单号","退款金额",	"充值券退款金额",	"退款类型","退款状态","商品名称","商户数据包","手续费","费率","订单金额","申请退款金额","费率备注");
        list.forEach(c -> head.add(Collections.singletonList(c)));
        List<TestHead> strings = ExcelUtils.readRt("/Users/chengw/myWorld/work/workFile/财务/对账/明细/支付分12.1.csv");
        System.out.println(strings);
    }

    @Test
    public void testTileToBase(){
        File file = new File("/Users/chengw/myWorld/personalFile/personalFile/陈高伟个人文件/10kV变电所及供配电系统设计三页.pdf");
        String base64 = FileUtils.PdfToBase64(file);
        System.out.print(base64);
    }


    @Test
    public void test66634() {

        String fileName2222 = "/Users/chengw/myWorld/lianyungang678";
        List<String> iCCIDSet222 = new ArrayList<>();
        try (BufferedReader br222 = new BufferedReader(new FileReader(fileName2222))) {
            String line222;
            while ((line222 = br222.readLine()) != null) {

                iCCIDSet222.add(line222.split("iccid:")[1].split(", vinC")[0] + ",");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String aa:iCCIDSet222){
            System.out.println(aa);
        }

    }
    @Test
    public void test999() throws IOException {
        List<String> bikeList = getBike();


        String fileName2222 = "/Users/chengw/myWorld/lianyungang678";
        List<String> iCCIDSet222 = new ArrayList<>();
        try (BufferedReader br222 = new BufferedReader(new FileReader(fileName2222))) {
            String line222;
            while ((line222 = br222.readLine()) != null) {
                if (bikeList.contains(line222.split("iccid:")[1].split(", vinC")[0].trim())){
                    iCCIDSet222.add(line222);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("/Users/chengw/myWorld/连云港26号到28号超时日志");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        iCCIDSet222.forEach(ic -> {
            try {
                bufferedWriter.write(ic);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bufferedWriter.close();

        System.out.println("678超市记录条数"+iCCIDSet222.size());

    }



    @Test
    public void test3452(){
        String fileName2222 = "/Users/chengw/myWorld/连云港26号到28号超时日志";
        List<String> iCCIDSet222 = new ArrayList<>();
        try (BufferedReader br222 = new BufferedReader(new FileReader(fileName2222))) {
            String line222;
            while ((line222 = br222.readLine()) != null) {
                System.out.println(line222.split(":")[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<String> getBike(){

        String fileName2222 = "/Users/chengw/myWorld/bike";
        List<String> iCCIDSet222 = new ArrayList<>();
        try (BufferedReader br222 = new BufferedReader(new FileReader(fileName2222))) {
            String line222;
            while ((line222 = br222.readLine()) != null) {

                iCCIDSet222.add(line222);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return iCCIDSet222;
    }

}
