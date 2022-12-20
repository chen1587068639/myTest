package com.example.test.filter;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CellColorSheetWriteHandler implements CellWriteHandler {

    //操作行
    private List<Integer> columnIndexs;
    //操作列
    private List<Integer> rowIndexs;
    //颜色
    private Short colorIndex;

    //构造
    public CellColorSheetWriteHandler(List<Integer> rowIndexs, List<Integer> columnIndexs, Short colorIndex) {
        this.rowIndexs = rowIndexs;
        this.columnIndexs = columnIndexs;
        this.colorIndex = colorIndex;
    }

    public CellColorSheetWriteHandler() {
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    /**
     * 设置excel的内容格式包括：字体，字颜色，字号，背景颜色，对齐，边框
     *
     * @param writeSheetHolder
     * @param writeTableHolder
     * @param cellDataList
     * @param cell
     * @param head
     * @param relativeRowIndex
     * @param isHead
     */
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (0 != cell.getRowIndex()) {
            if (3 > cell.getRowIndex()) {
                // 根据单元格获取workbook
                Workbook workbook = cell.getSheet().getWorkbook();
                //设置行高
                writeSheetHolder.getSheet().getRow(cell.getRowIndex()).setHeight((short) (1.4 * 256));
                // 单元格策略
                WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
                // 设置背景颜色白色
                // 设置垂直居中为居中对齐
                contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                // 设置左右对齐为靠左对齐
                contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
                // 设置单元格上下左右边框为细边框
                contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
                contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
                contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
                contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
                // 创建字体实例
                WriteFont cellWriteFont = new WriteFont();
                // 设置字体大小
                cellWriteFont.setFontHeightInPoints((short) 12);
                cellWriteFont.setColor(colorIndex);
                contentWriteCellStyle.setWriteFont(cellWriteFont);
                if ((cell.getRowIndex() & 1) == 0) {
                    contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                    CellStyle cellStyle = StyleUtil.buildHeadCellStyle(workbook, contentWriteCellStyle);
                    cellStyleMap.put("whiteStyle", cellStyle);
                } else {
                    contentWriteCellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE1.getIndex());
                    CellStyle cellStyle = StyleUtil.buildHeadCellStyle(workbook, contentWriteCellStyle);
                    cellStyleMap.put("blueStyle", cellStyle);
                }
            }
            if ((cell.getRowIndex() & 1) == 0) {
                cell.setCellStyle(cellStyleMap.get("whiteStyle"));
            } else {
                cell.setCellStyle(cellStyleMap.get("blueStyle"));
            }
        }
    }

    public Map<String, CellStyle> cellStyleMap = new HashMap<>();
}
