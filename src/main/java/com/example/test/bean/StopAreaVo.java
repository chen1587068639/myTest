package com.example.test.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

/**
 * @Author: chengw
 * @Date: 2023/4/26 下午3:24
 */
@Data
public class StopAreaVo {

    /**
     * 订单号
     */
    @ExcelProperty(value = "停车点名称", index = 0)
    @ColumnWidth(value = 0)
    private String name;

    /**
     * 取车经度
     */
    @ExcelProperty(value = "纬度", index = 1)
    @ColumnWidth(value = 1)
    private Double Latitude;

    /**
     * 取车纬度
     */
    @ExcelProperty(value = "经度", index = 2)
    @ColumnWidth(value = 2)
    private Double Longitude;

    /**
     * 颜色
     */
    @ExcelProperty(value = "颜色", index = 3)
    @ColumnWidth(value = 3)
    private String color;

}
