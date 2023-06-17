package com.example.test.collection;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.test.bean.OrderVo;
import com.example.test.bean.StopAreaVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 地图
 * @Author: chengw
 * @Date: 2023/1/9 上午10:43
 */
@SpringBootTest
public class MapTest {


    /**
     * 测试百度画地图
     */
    @Test
    public void testBaiDu() {
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        Double[] lats = {31.2304, 39.9042, 40.7128, 37.7749, 34.0522};
        Double[] lngs = {121.4737, 116.4074, -74.0060, -122.4194, -118.2437};
        String[] colors = {"green", "green", "green", "green", "green"};
        String[] names = {"Shanghai", "Beijing", "NewYork", "SanFrancisco", "LosAngeles"};
        String filename = "/Users/chengw/myWorld/map";
        plotByBaiDu(lats, lngs, colors, names,filename,accessKey);
    }



    /**
     * 沂南上班
     */
    @Test
    public void testBaiDuByYINAN1() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "沂南上班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/沂南上班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }




    /**
     * 沂南下班
     */
    @Test
    public void testBaiDuByYINAN1222() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "沂南下班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/沂南下班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }





    /**
     * 兰山上班
     */
    @Test
    public void testBaiDuByLANSHSN() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "兰山上班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/兰山上班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }



    /**
     * 兰山下班
     */
    @Test
    public void testBaiDuByLANSHSN222() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "兰山下班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/兰山下班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }



    /**
     * 静海上班
     */
    @Test
    public void testBaiDuByJINGHIA() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "静海上班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/静海上班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }




    /**
     * 静海下班
     */
    @Test
    public void testBaiDuByJINGHIA222() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "静海下班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/静海下班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }



    /**
     * 测试静海下班
     */
    @Test
    public void testBaiDuByJINGHIA23333() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/" + "静海下班高峰期停车点.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/四月/16周/静海测试2下班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }


    /**
     * 百度地图
     * @param lats
     * @param lngs
     * @param colors
     * @param names
     */
    public static void plotByBaiDu(Double[] lats, Double[] lngs, String[] colors, String[] names,String filename,String accessKey) {
        try {
            //拼接请求URL
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append("https://api.map.baidu.com/staticimage/v2");
            //加密钥
            urlBuilder.append("?ak=").append(accessKey);
//            urlBuilder.append("&size=800x600");
            //返回图片大小会根据此标志调整。取值范围为1或2：
            urlBuilder.append("&scale=2");
            //地图级别。高清图范围[3, 18]；低清图范围[3,19]
            urlBuilder.append("&zoom=13");
//            地图中心点位置
//            urlBuilder.append("&center=116.403874,39.914888");
//            urlBuilder.append("&width=600&height=400");
            // 添加标点
//            for (int i = 0; i < lngs.length; i++) {
//                urlBuilder.append("&markers=");
//                urlBuilder.append(lngs[i]).append(",").append(lats[i]).append(",");
//                urlBuilder.append("large").append(",").append("color:").append(colors[i]).append(",");
//                urlBuilder.append("label:").append(names[i]);
//            }
            //取车点（前二十个是取车点）添加到地图上：添加成红色标点
            urlBuilder.append("&markers=");
            for (int i = 0; i < 19; i++) {
                urlBuilder.append(lngs[i]).append(",").append(lats[i]).append("|");
            }
            urlBuilder.append(lngs[19]).append(",").append(lats[19]);
            //给取车点标记添加格式
//            urlBuilder.append("&markerStyles=");
//            urlBuilder.append("s,A,").append(0xFF0000);

            //还车点（后二十个是取车点）添加到地图上：添加成绿色标签
            urlBuilder.append("&labels=");
            for (int i = 20 ; i < colors.length-1 ; i++ ){
                urlBuilder.append(lngs[i]).append(",").append(lats[i]).append("|");
            }
            urlBuilder.append(lngs[colors.length-1]).append(",").append(lats[colors.length-1]);
            //给还车点标签添加格式
            urlBuilder.append("&labelStyles=1,1,12,0x008000,0x008000,1");


            System.out.println(urlBuilder);

            // 发送请求并获取响应内容
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            InputStream inputStream = connection.getInputStream();

            // 读取响应内容并保存为图片文件
            BufferedImage image = ImageIO.read(inputStream);
            File file = new File(filename + ".png");
            ImageIO.write(image, "png", file);
            // 关闭连接和输入流
            inputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }









    /**
     * 静海上班
     */
    @Test
    public void testBaiDuByTuanBo() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/五月/十九周/" + "团泊上班取车.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/五月/十九周/团泊上班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }




    /**
     * 静海下班
     */
    @Test
    public void testBaiDuByTuanBo222() {
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/分析/五月/十九周/" + "团泊下班取车.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(StopAreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<StopAreaVo> stopAreaVoList = beforeDispatchBike.doReadSync();

        System.out.println(stopAreaVoList.toString());
        String accessKey = "5YNfrz5za5IvXtDppaV8TuG7FYAsoeBC";
        String filename = "/Users/chengw/myWorld/work/workFile/分析/五月/十九周/团泊下班高峰期停车点";

        List<Double> latList = new ArrayList<>();
        List<Double> lonList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        stopAreaVoList.forEach(stopAreaVo -> {
            latList.add(stopAreaVo.getLatitude());
            lonList.add(stopAreaVo.getLongitude());
            colorList.add(stopAreaVo.getColor());
            nameList.add(stopAreaVo.getName());
        });
        plotByBaiDu(latList.toArray(new Double[stopAreaVoList.size()]), lonList.toArray(new Double[stopAreaVoList.size()]), colorList.toArray(new String[stopAreaVoList.size()]), nameList.toArray(new String[stopAreaVoList.size()]),filename,accessKey);
    }


}
