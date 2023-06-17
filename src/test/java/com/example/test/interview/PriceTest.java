package com.example.test.interview;

import lombok.Data;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: chengw
 * @Date: 2023/6/13 下午6:36
 */

@SpringBootTest
public class PriceTest {

    public final static SimpleDateFormat S_Y_M_D_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 商品价格数组：写后读先
     */
    public CopyOnWriteArrayList<CommodityPrice> commodityPriceList = new CopyOnWriteArrayList<>();

    /**
     * 比价金额计算：同一个商品在不同平台的同一个时间有多个价格，请描绘一个品在整个生命周期内的最低价格。
     *
     * 例如，商品A
     * 在平台A 10:00 - 12:00的价格为5
     * 在平台B 11:30 - 14:30的价格为3
     * 在平台C 14:00 -16:00的价格为2
     *
     * 那么全生命周期的最低价格（忽略平台）为
     * 10:00 - 11:30的价格为5
     * 11:30 - 14:00的价格为3
     * 14:00 - 16:00的价格为2
     *
     * 请尝试多种corner case下的解题结果
     *
     * 要编码，笔试题要和简历一起推，您看下能做的话再联系
     */
    public void test56() throws ParseException {
        CommodityPrice commodityPriceByA = new CommodityPrice(S_Y_M_D_FORMAT.parse("2023-05-01 10:00:00"),S_Y_M_D_FORMAT.parse("2023-05-02 12:00:00"),new BigDecimal(3));
        CommodityPrice commodityPriceByB = new CommodityPrice(S_Y_M_D_FORMAT.parse("2023-05-01 11:30:00"),S_Y_M_D_FORMAT.parse("2023-05-02 14:30:00"),new BigDecimal(3));
        CommodityPrice commodityPriceByC = new CommodityPrice(S_Y_M_D_FORMAT.parse("2023-05-01 14:00:00"),S_Y_M_D_FORMAT.parse("2023-05-02 16:00:00"),new BigDecimal(3));
        List<CommodityPrice> commodityPriceList = new ArrayList<>();

    }
    private void countPrice(List<CommodityPrice> commodityPriceList){
        //排序
        commodityPriceList.sort(Comparator.comparing(c -> (Date)c.getStartTime()));
        commodityPriceList.forEach(commodityPrice -> {

        });
    }

}
@Data
class CommodityPrice{
    Date StartTime;
    Date endTime;
    BigDecimal price;

    public CommodityPrice() {
    }

    public CommodityPrice(Date startTime, Date endTime, BigDecimal price) {
        StartTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }
}
