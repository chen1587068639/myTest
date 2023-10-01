package com.example.test.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AreaVo implements Serializable {

    /**
     * 区域id
     */
    @ExcelProperty("停车点ID")
    private Long areaId;

    /**
     * 名称
     */
    @ExcelProperty("运营区域名称")
    private String areaName;

    /**
     * 运营区id
     */
    @ExcelProperty("运营区域ID")
    private Long operateAreaId;

    @ExcelProperty("取车数量")
    private String takeNum;
    @ExcelProperty("还车数量")
    private String returnNum;
    @ExcelProperty("取车方差")
    private String takeVariance;
    @ExcelProperty("还车方差")
    private String returnVariance;
    @ExcelProperty("取车总数")
    private String takeSum;
    @ExcelProperty("还车总数")
    private String returnSum;
    @ExcelProperty("取车平均数")
    private String taskAverage;
    @ExcelProperty("还车平均数")
    private String returnAverage;
    @ExcelProperty("统计时间")
    private String countTime;

}

