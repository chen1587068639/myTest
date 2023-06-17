package com.example.test.util.file;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: chengw
 * @Date: 2022/12/28 下午2:22
 */
@Data
public class TestHead {

    @ExcelProperty("交易时间")
    private String Date;
}
