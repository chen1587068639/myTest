package com.example.test.number;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengw
 * @Date: 2023/1/5 下午2:39
 */
@SpringBootTest
public class AddressTest {

    /**
     * 中心
     */
    @Test
    public void testCenter(){
        Map<Double,Double> areaPointMap = new HashMap<>();
        areaPointMap.put(32.137832,114.046021);
        areaPointMap.put(32.137807,114.045919);
        areaPointMap.put(32.137403,114.04601);
        areaPointMap.put(32.137421,114.046107);

        System.out.println(areaPointCount(areaPointMap));
    }

    /**
     * 计算位置中心
     * @return
     */
    private Map<Double,Double> areaPointCount(Map<Double,Double> areaPointMap){
        Map<Double, Double> returnMap = new HashMap<>();
        double x = 0D;
        double y = 0D;
        for (Map.Entry<Double,Double> map : areaPointMap.entrySet()){
            x = x + map.getKey();
            y = y + map.getValue();
        }
        returnMap.put(x/areaPointMap.size(),y/areaPointMap.size());
        return returnMap;
    }

    private void test(Map<Double, Double> returnMap){
        Point2D.Double p = new Point2D.Double();
        p.setLocation(10D,12D);

    }

    /**
     * 判断点是否在围栏内
     *
     * @param point
     * @param polygon
     * @return
     */
    public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
        if (CollectionUtils.isEmpty(polygon)) {
            return false;
        }
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        int size = polygon.size();
        for (int i = 1; i < size; i++) {
            Point2D.Double aDouble = polygon.get(i);
            p.lineTo(aDouble.x, aDouble.y);
        }
        p.lineTo(first.x, first.y);

        p.closePath();

        return p.contains(point);
    }

    @Test
    public void test22(){
        Point2D.Double p0 = new Point2D.Double();
        p0.setLocation(32.1378319,114.0460209);
        List<Point2D.Double> polygon = new ArrayList<>();
        Point2D.Double p1 = new Point2D.Double();
        p1.setLocation(32.137832,114.046021);
        Point2D.Double p2 = new Point2D.Double();
        p2.setLocation(32.137807,114.045919);
        Point2D.Double p3 = new Point2D.Double();
        p3.setLocation(32.137403,114.04601);
        Point2D.Double p4 = new Point2D.Double();
        p4.setLocation(32.137421,114.046107);

        polygon.add(p1);
        polygon.add(p2);
        polygon.add(p3);
        polygon.add(p4);
        System.out.println(testCheckWithJdkGeneralPath(p0,polygon));
    }

    /**
     * 判断点距离停车区的距离
     *
     * @param point
     * @param polygon
     * @return
     */
    public static boolean testCheckWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
        if (CollectionUtils.isEmpty(polygon)) {
            return false;
        }
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        int size = polygon.size();
        for (int i = 1; i < size; i++) {
            Point2D.Double aDouble = polygon.get(i);
            p.lineTo(aDouble.x, aDouble.y);
        }
        p.lineTo(first.x, first.y);

        p.closePath();
        return p.contains(point);
    }

}
