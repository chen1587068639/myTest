package com.example.test.io.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.test.bean.AreaVo;
import com.example.test.bean.OrderVo;
import com.example.test.util.DateUtils;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: chengw
 * @Date: 2023/4/14 下午10:36
 */
@SpringBootTest
public class ExcelTest {


    @Test
    public void test234() {
        String path = "/Users/chengw/myWorld/";
        String areaInfo = "停车点名称数据.xlsx";
        String orderParkInfo = "21号团泊.xlsx";
        ExcelReaderSheetBuilder sheet = EasyExcel.read(path + areaInfo).head(AreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<AreaVo> parkList = sheet.doReadSync();


        ExcelReaderSheetBuilder sheet1 = EasyExcel.read(path + orderParkInfo).head(AreaVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<AreaVo> orderParkList = sheet1.doReadSync();
        System.out.println(orderParkList.toString());

        orderParkList.forEach(orderPark -> {
            parkList.forEach(park -> {
                if (orderPark.getAreaId().equals(park.getAreaId())) {
                    orderPark.setAreaName(park.getAreaName());
                    orderPark.setOperateAreaId(park.getOperateAreaId());
                }
            });
        });
        EasyExcel.write(path + "21号团泊finish.xlsx",AreaVo.class).sheet("结果").doWrite(orderParkList);
    }









    /**
     * 读取订单数据修改每天的车牌号做假数据
     *
     * <p>3. 直接读即可
     */
    @Test
    public void indexOrNameRead() throws Exception{
        //生成一个280-300的随机数字
        //int random = 280 + (int) (Math.random() * 21);

        Date timeLine = DateUtils.D_F.parse("2022-09-01");
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/运维/假数据/" + "调度之前.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(OrderVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<OrderVo> beforeDispatchBikeList = beforeDispatchBike.doReadSync();
        System.out.println("-----------读取调度之前车辆信息完毕----------------");
        //读取调度之后车辆信息
        String afterDispatch = "/Users/chengw/myWorld/work/workFile/运维/假数据/" + "调度之后.xlsx";
        ExcelReaderSheetBuilder afterDispatchBike = EasyExcel.read(afterDispatch).head(OrderVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<OrderVo> afterDispatchBikeList = afterDispatchBike.doReadSync();
        System.out.println("-----------读取调度之后车辆信息完毕----------------");

        //读取订单数据
        String fileName = "/Users/chengw/myWorld/work/workFile/运维/假数据/" + "紧急需求.xlsx";
        // 这里默认读取第一个sheet
        ExcelReaderSheetBuilder sheet = EasyExcel.read(fileName).head(OrderVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<OrderVo> orderVoList = sheet.doReadSync();
        //打乱数据
        Collections.shuffle(orderVoList);


        //将读取到的订单分每日注入map中
        Map<String, List<OrderVo>> orderVoListByMap = orderVoList.stream().collect(Collectors.groupingBy(item -> item.getCreateTime().split(" ")[0]));
        //每个map
        orderVoListByMap.forEach((k,v) -> {

            //将数组打乱，再更新车牌号
            Collections.shuffle(v);

            try {
                Date time = DateUtils.D_F.parse(k);
                //生成随机数280-300的随机数
                int random = 280 + (int) (Math.random() * 21);
                if (time.before(timeLine)){
                    //在九月之前
                    //修改所有订单数据
                    v.forEach(orderVo -> {
                        orderVo.setPlateNo(beforeDispatchBikeList.get(getData(beforeDispatchBikeList)).getPlateNo());
                    });
                    //批量修改前random条订单的车牌号
                    for (int i = 0 ; i < (Math.min(v.size(), random));i++){
                        OrderVo orderVo = v.get(i);
                        orderVo.setPlateNo(beforeDispatchBikeList.get(i).getPlateNo());
                    }
                } else {
                    //在九月之后
                    //修改所有订单数据
                    v.forEach(orderVo -> {
                        orderVo.setPlateNo(afterDispatchBikeList.get(getData(afterDispatchBikeList)).getPlateNo());
                    });
                    //批量修改前random条订单的车牌号
                    for (int i = 0 ; i < (Math.min(v.size(), random));i++){
                        OrderVo orderVo = v.get(i);
                        orderVo.setPlateNo(afterDispatchBikeList.get(i).getPlateNo());
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        //把修改后的数据放到list中输出成文件
        List<OrderVo> resultList = new ArrayList<>();
        orderVoListByMap.forEach((k,v) -> {
            resultList.addAll(v);
        });
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/chengw/myWorld/work/workFile/运维/假数据/" + "周末晚上.xlsx");
        EasyExcel.write(fileOutputStream,OrderVo.class).sheet("结果").doWrite(resultList);

        System.out.println("假数据修改完成");
        Map<String, List<OrderVo>> collect = resultList.stream().collect(Collectors.groupingBy(OrderVo::getPlateNo));
        System.out.println(collect.size());
    }



    @Test
    public void indexOrNameRead111() throws Exception{
        //生成一个280-300的随机数字
        //int random = 280 + (int) (Math.random() * 21);

        Date timeLine = DateUtils.D_F.parse("2022-09-01");
        //读取调度之前车辆信息
        String beforeDispatch = "/Users/chengw/myWorld/work/workFile/运维/假数据/" + "调度之前.xlsx";
        ExcelReaderSheetBuilder beforeDispatchBike = EasyExcel.read(beforeDispatch).head(OrderVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<OrderVo> beforeDispatchBikeList = beforeDispatchBike.doReadSync();
        System.out.println("-----------读取调度之前车辆信息完毕----------------");
        //读取调度之后车辆信息
        String afterDispatch = "/Users/chengw/myWorld/work/workFile/运维/假数据/" + "调度之后.xlsx";
        ExcelReaderSheetBuilder afterDispatchBike = EasyExcel.read(afterDispatch).head(OrderVo.class).excelType(ExcelTypeEnum.XLSX).sheet();
        List<OrderVo> afterDispatchBikeList = afterDispatchBike.doReadSync();
        System.out.println("-----------读取调度之后车辆信息完毕----------------");
        getData(afterDispatchBikeList);
        afterDispatchBikeList.forEach(a -> {
            Integer data = getData(afterDispatchBikeList);
            System.out.println(afterDispatchBikeList.get(data).getPlateNo());
        });
    }


    private static Integer getData(List<OrderVo> beforeDispatchBikeList){
        Random rand = new Random();
        return rand.nextInt(beforeDispatchBikeList.size());
    }
}
