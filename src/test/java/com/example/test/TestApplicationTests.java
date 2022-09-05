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
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");

    @Test
    void contextLoads() throws IOException {
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
        String url = "https://bike.mofangchuxing.com/admin/users/list";
        Map<String,Object> map = new HashMap<>();
        map.put("t","zSdQRH4XW8lTWeLBCPb9qufw4");
        String body = JSONObject.toJSONString(map);
        String responseBody = HttpUtils.postHttp(url,body);
        log.info("2222:{}",responseBody);
        JSONObject jsonObject =  JSONObject.parseObject(responseBody);
        log.info("json:{}",jsonObject);
    }



    @Test
    void sdf() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String userName = "zzcx";
        String appKey = "chdi7mnks0n0n9xy07";
        String appSecret = "9l52ho2sd3fv9hweok0ad6qwu0cvuhde";
        String timeStamp = sdf.format(new Date());

        String signTemp = "appKey=" + appKey + "&timeStamp=" + timeStamp +"&userName=" + userName + "&appSecret=" + appSecret;
        String sign = MD5Utils.getMD5(signTemp);
        String iccid = "89860490192070729950";

        String param = "appKey=" + appKey;
        param += "&timeStamp=" + timeStamp;
        param += "&userName=" + userName;
        param += "&sign=" + sign;
        param += "&iccid=" + iccid;
        log.info("111:{}",param);
        Map<String,Object> map = new HashMap<>();
        map.put("appKey",appKey);
        map.put("timeStamp",timeStamp);
        map.put("userName",userName);
        map.put("sign",sign);
        map.put("iccid",iccid);
        StringBuilder s = new StringBuilder();
        for (Map.Entry<String,Object> m:map.entrySet()){
            s.append(m.getKey()).append("=").append(m.getValue());
        }
        String result = sendPost("http://121.89.194.200:8082/admin/api/v2/cardInfo",param);
        System.out.println(result);
    }


    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置文件类型:
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }




    /**
     * 导出表头必填字段
     * @param outputStream 输入流
     * @param dataList 导入数据
     * @param headList 表头列表
     * @param sheetName sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, List<String> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {
        List<List<String>> list = new ArrayList<>();
        if(headList != null){
            headList.forEach(h -> list.add(Collections.singletonList(h)));
        }

        // 头的策略
        WriteFont writeFont = new WriteFont();
        writeFont.setColor(IndexedColors.WHITE.index);
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setWriteFont(writeFont);
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream).head(list).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
//        if(null != cellWriteHandlers && cellWriteHandlers.length>0){
//            for(int i = 0 ; i < cellWriteHandlers.length;i++){
//                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
//            }
//        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }
    /**
     * 导出表头必填字段标红色
     * @param outputStream 输入流
     * @param dataList 导入数据
     * @param headList 表头列表
     * @param sheetName sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, Class<? extends Object> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream,headList).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if(null != cellWriteHandlers && cellWriteHandlers.length>0){
            for(int i = 0 ; i < cellWriteHandlers.length;i++){
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 3; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


























    public Date addOneDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR,+1);
        return calendar.getTime();
    }

    /**
     * 得到周一时间
     * @param date
     * @return
     */
    private Date getMondayOfWeekLL(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        return cal.getTime();
    }
    public static Date addOneMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,1);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
    public static Date getLastMomentOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime();
    }

    //得出本年的xxxx:i+1:j日期
    public static Date getDateByThisYear(int y,int i,int j){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY,0);
        todayEnd.set(Calendar.MINUTE, 0);
        todayEnd.set(Calendar.SECOND,0);
        todayEnd.set(Calendar.YEAR,y);
        todayEnd.set(Calendar.MONTH,i);
        todayEnd.set(Calendar.DATE,j);
        return todayEnd.getTime();
    }

    private static final char[] FIX_STR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final String STR = new String(FIX_STR);

    private static String strToSplitHex16() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "") + new Random().nextInt(10);
        StringBuilder shortBuffer = new StringBuilder();
        //我们这里想要保证券码为11位，所以32位uuid加了一位随机数，再分成11等份；（如果是8位券码，则32位uuid分成8等份进行计算即可）
        for (int i = 0; i < 11; i++) {
            String str = uuid.substring(i * 3, i * 3 + 3);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(FIX_STR[x % FIX_STR.length]);
        }
        return shortBuffer.toString();
    }






    /**
     * 传入周日得到下周日
     * @param date
     * @return
     */
    public Date getNextSunday(Date date){
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(date);
        lastCalendar.add(Calendar.DAY_OF_YEAR,7);
        return lastCalendar.getTime();
    }
    /**
     * 传入周日得到上周日
     * @param date
     * @return
     */
    public Date getLastSunday(Date date){
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(date);
        lastCalendar.add(Calendar.DAY_OF_YEAR,-7);
        lastCalendar.set(Calendar.HOUR_OF_DAY,0);
        lastCalendar.set(Calendar.MINUTE, 0);
        lastCalendar.set(Calendar.SECOND,0);
        return lastCalendar.getTime();
    }
    /**
     * 传入周一时间，得到周日
     * @param date
     * @return
     */
    static Date getSundayOfWeek(Date date) {
        Date monday = getMondayOfWeek(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(monday);
        cal.add(Calendar.DATE, 6);
        //cal.set(Calendar.HOUR_OF_DAY,0);
        return cal.getTime();
    }

    /**
     * 得到周一时间
     * @param date
     * @return
     */
    static Date getMondayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    public static Long diffDay(Date p1,Date p2){
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(p1);
        instance2.setTime(p2);
        long day = (p1.getTime() - p2.getTime()) /1000/ 60 / 60 /24;
        return day;
    }

    public static BigDecimal getMedian(List<BigDecimal> total) {
        BigDecimal j;
        //集合排序
        Collections.sort(total);
        int size = total.size();
        if(size % 2 == 1){
            j = total.get((size-1)/2);
        }else {
            System.out.println(total.get(size/2-1));
            System.out.println(total.get(size/2));
            j = ((total.get(size/2-1).add(total.get(size/2)))).divide(new BigDecimal(2),2,0);
        }

        return j;

    }

}
