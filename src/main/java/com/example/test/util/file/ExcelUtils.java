package com.example.test.util.file;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.Data;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelUtils {


    /**
     * 导出表头必填字段
     *
     * @param outputStream      输入流
     * @param dataList          导入数据
     * @param headList          表头列表
     * @param sheetName         sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, List<String> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {
        List<List<String>> list = new ArrayList<>();
        if (headList != null) {
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
     *
     * @param outputStream      输入流
     * @param dataList          导入数据
     * @param headList          表头列表
     * @param sheetName         sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, Class<? extends Object> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream, headList).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if (null != cellWriteHandlers && cellWriteHandlers.length > 0) {
            for (int i = 0; i < cellWriteHandlers.length; i++) {
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }

    public static List<LinkedHashMap<String,String>> readCSV(List<List<String>> head,File file){
        ExcelReaderBuilder excelReaderBuilder = EasyExcel.read(file).excelType(ExcelTypeEnum.CSV);

        return excelReaderBuilder.head(head).doReadAllSync();
    }

    public static List<String> readCSVTest(List<List<String>> head,File file,Integer sheetName){
        //ExcelReaderSheetBuilder sheet = EasyExcel.read(file).excelType(ExcelTypeEnum.CSV).sheet();
        ExcelReaderSheetBuilder excelReaderSheetBuilder = EasyExcel.read(file).excelType(ExcelTypeEnum.CSV).sheet(0);

        return excelReaderSheetBuilder.doReadSync();
    }

    public static List<TestHead> readRd(String filePath){
        List<TestHead> rtList = new ArrayList<>();
        //System.out.println(datalist.size()+"=");
        EasyExcel.read(filePath,TestHead.class,
                new PageReadListener<TestHead>(rtList::addAll)).sheet().doRead();
        return rtList;
    }

    public static List<TestHead> readRd11(String filePath){
        List<TestHead> rdList = new ArrayList<>();
        EasyExcel.read(filePath, TestHead.class, new ReadListener<TestHead>() {
            @Override
            public void invoke(TestHead data, AnalysisContext context) {
                rdList.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println(rdList.size());
            }
        }).charset(StandardCharsets.UTF_8).sheet().doRead();
        return rdList;
    }

    public static List<TestHead> readRt(String filePath){
        List<TestHead> rtList = new ArrayList<>();
        //System.out.println(datalist.size()+"=");
        EasyExcel.read(filePath, TestHead.class,
                new PageReadListener<TestHead>(rtList::addAll)).sheet().doRead();
        //System.out.println(rtList.size()+"==");
        return rtList;
    }



}
