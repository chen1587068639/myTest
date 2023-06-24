package com.example.test.interview;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
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

    @Data
    public static class CommodityPrice{
        Date startTime;
        Date endTime;
        int price;

        public CommodityPrice() {
        }

        public CommodityPrice(Date startTime, Date endTime, Integer price) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.price = price;
        }
    }

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
     */
    @Test
    public void test56() throws ParseException {
        CommodityPrice commodityPriceByA = new CommodityPrice(DATE_FORMAT.parse("2023-05-01 10:00:00"),DATE_FORMAT.parse("2023-05-03 10:30:00"),10);
        CommodityPrice commodityPriceByB = new CommodityPrice(DATE_FORMAT.parse("2023-05-01 11:30:00"),DATE_FORMAT.parse("2023-05-02 14:30:00"),4);
        CommodityPrice commodityPriceByC = new CommodityPrice(DATE_FORMAT.parse("2023-05-02 15:00:00"),DATE_FORMAT.parse("2023-05-02 16:00:00"),8);
        List<CommodityPrice> commodityPriceList = new ArrayList<>();
        commodityPriceList.add(commodityPriceByA);
        commodityPriceList.add(commodityPriceByB);
        commodityPriceList.add(commodityPriceByC);
        List<CommodityPrice> minimumPrices = calculateMinimumPrices(commodityPriceList);
        for (CommodityPrice price : minimumPrices) {
            System.out.println(DATE_FORMAT.format(price.getStartTime()) + " - " + DATE_FORMAT.format(price.getEndTime()) + " : " + price.getPrice());
        }
    }


    public static List<CommodityPrice> calculateMinimumPrices(List<CommodityPrice> prices) {
        List<Date> timePoints = new ArrayList<>();
        for (CommodityPrice price : prices) {
            timePoints.add(price.getStartTime());
            timePoints.add(price.getEndTime());
        }

        timePoints.sort(Comparator.naturalOrder());
        //根据结束时间排序
        prices.sort(Comparator.comparing(CommodityPrice::getEndTime));
        List<CommodityPrice> minimumPrices = new ArrayList<>();
        int currentIndex = 0;
        for (int i = 0; i < timePoints.size() - 1; i++) {

            Date startTime = timePoints.get(i);
            Date endTime = timePoints.get(i + 1);

            int minimumPrice = Integer.MAX_VALUE;

            for (int j = currentIndex; j < prices.size(); j++) {
                CommodityPrice price = prices.get(j);
                if (price.getEndTime().compareTo(startTime) < 0) {
                    currentIndex++;
                    continue;
                }
                if (price.getStartTime().compareTo(startTime) <= 0 && price.getEndTime().compareTo(endTime) >= 0) {
                    minimumPrice = Math.min(minimumPrice, price.getPrice());
                }
            }
            if (minimumPrice != Integer.MAX_VALUE) {
                if (minimumPrices.size() > 0 && minimumPrice == minimumPrices.get(minimumPrices.size() - 1).getPrice() && minimumPrices.get(minimumPrices.size() - 1).getEndTime().equals(startTime)) {
                    minimumPrices.get(minimumPrices.size() - 1).setEndTime(endTime);
                } else {
                    minimumPrices.add(new CommodityPrice(startTime, endTime, minimumPrice));
                }
            }

        }

        return minimumPrices;
    }


}

