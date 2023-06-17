package com.example.test.collection;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterTableBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @Author: chengw
 * @Date: 2022/12/22 下午3:38
 */
@SpringBootTest
public class ListTest {

    @Test
    public void testList(){
        List<String> newList = new ArrayList<>();
        newList.add("对方改变");
        newList.add("而为");
        newList.add("阿斯顿");
        newList.add("修复");
        List<String> oldList = new ArrayList<>();
        oldList.add("而为");
        oldList.add("阿斯顿");
        oldList.add("修复");
        newList.removeAll(oldList);
        System.out.println("newList:" + newList);
        System.out.println("oldList:" + oldList);

        String fileName = "/Users/chengw/myWorld" + "test" +  + System.currentTimeMillis() + ".xlsx";
        ExcelWriter excelWriter = EasyExcel.write(fileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().needHead(Boolean.FALSE).build();
        // 这里必须指定需要头，table 会继承sheet的配置，sheet配置了不需要，table 默认也是不需要
        WriteTable writeTable0 = EasyExcel.writerTable(0).needHead(Boolean.TRUE).build();
        WriteTable writeTable1 = EasyExcel.writerTable(1).needHead(Boolean.TRUE).build();

        excelWriter.write(oldList, writeSheet, writeTable0);
        excelWriter.write(oldList, writeSheet, writeTable1);
        excelWriter.finish();

    }

    @Test
    public void dynamicHeadWrite() {
        List<String> newList = new ArrayList<>();
        newList.add("对方改变");
        newList.add("而为");
        newList.add("阿斯顿");
        newList.add("修复");
        List<String> oldList = new ArrayList<>();
        oldList.add("而为");
        oldList.add("阿斯顿");
        oldList.add("修复");
        newList.removeAll(oldList);
        System.out.println("newList:" + newList);
        System.out.println("oldList:" + oldList);
        List<List<String>> headList = ListUtils.newArrayList();
        List<String> head0 = ListUtils.newArrayList();
        head0.add("字符串" + System.currentTimeMillis());
        headList.add(head0);
        List<List<Object>> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            List<Object> data = ListUtils.newArrayList();
            data.add("列值" + i);
            list.add(data);
        }
        String fileName = "/Users/chengw/myWorld/" + "dynamicHeadWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(headList).sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(list);

    }
    /**
     * 不创建对象的写
     */
    @Test
    public void noModelWrite() {
        // 写法1
        String fileName = "/Users/chengw/myWorld" + "noModelWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").table(0).doWrite(dataList());
    }

    private List<List<String>> head() {
        List<List<String>> list = ListUtils.newArrayList();
        List<String> head0 = ListUtils.newArrayList();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = ListUtils.newArrayList();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = ListUtils.newArrayList();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            List<Object> data = ListUtils.newArrayList();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }
}
