package com.example.test.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @Author: chengw
 * @Date: 2023/4/14 下午10:44
 */
@Data
public class OrderVo {

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号", index = 0)
    @ColumnWidth(value = 0)
    private String orderNo;

    /**
     * 车牌号
     */
    @ExcelProperty(value = "车牌号", index = 1)
    @ColumnWidth(value = 1)
    private String plateNo;

    /**
     * 订单开锁时间
     */
    @ExcelProperty(value = "订单开锁时间", index = 2)
    @ColumnWidth(value = 2)
    private String createTime;

    /**
     * 订单关锁时间
     */
    @ExcelProperty(value = "订单关锁时间", index = 3)
    @ColumnWidth(value = 3)
    private String endTime;

    /**
     * 取车经度
     */
    @ExcelProperty(value = "取车经度", index = 4)
    @ColumnWidth(value = 4)
    private String takeUserLongitude;

    /**
     * 取车纬度
     */
    @ExcelProperty(value = "取车纬度", index = 5)
    @ColumnWidth(value = 5)
    private String takeUserLatitude;

    /**
     * 还车经度
     */
    @ExcelProperty(value = "还车经度", index = 6)
    @ColumnWidth(value = 6)
    private String returnUserLongitude;

    /**
     * 还车纬度
     */
    @ExcelProperty(value = "还车纬度", index = 7)
    @ColumnWidth(value = 7)
    private String returnUserLatitude;

    /**
     * 取车地址
     */
    @ExcelProperty(value = "取车地址", index = 8)
    @ColumnWidth(value = 8)
    private String userTakeAddress;

    /**
     * 还车地址
     */
    @ExcelProperty(value = "还车地址", index = 9)
    @ColumnWidth(value = 9)
    private String userReturnAddress;

    /**
     * 订单时长（分钟）
     */
    @ExcelProperty(value = "订单时长（分钟）", index = 10)
    @ColumnWidth(value = 10)
    private String orderTime;


    /**
     * 订单里程
     */
    @ExcelProperty(value = "订单里程", index = 11)
    @ColumnWidth(value = 11)
    private String orderMileage;
}
