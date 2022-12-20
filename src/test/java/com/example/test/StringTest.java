package com.example.test;

import com.example.test.util.StringUtils;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
class Location {
    private BigDecimal lon;
    private BigDecimal lat;
}

@SpringBootTest
public class StringTest {

    /**
     * 下标分隔转成大写字母分隔
     */
    @Test
    public void subscriptTurnCapital(){
//        String tempName = "MyNameIsIceClean".replaceAll("[A-Z]", "_$0");
//        System.out.println(tempName);
//        String[] partName = tempName.split("_");
//        for (String name : partName){
//            System.out.print(name + " ");
//        }
        String bike_id = subscriptTurnCapital("bike_id");
        System.out.println(bike_id);
    }

    @Test
    public void asdfasdf(){
        StringBuilder sqlJoint = new StringBuilder();
        List<String> stringList = new ArrayList<>();
        stringList.add("order_pay_money_amount_id");
        stringList.add("order_amount");
        if (CollectionUtils.isNotEmpty(stringList)){
            stringList.forEach(c ->
                    {
                        sqlJoint.append("SUM(").append(c).append(") AS ").append(StringUtils.subscriptTurnCapital(c)).append(",");
                    }
            );
            //sqlJoint.delete(sqlJoint.length()-1,sqlJoint.length());
            sqlJoint.append("COUNT(*) AS num");
        }
        System.out.println(sqlJoint);
    }

    public static String subscriptTurnCapital(String oldString){
        String newString = oldString;
        if (oldString.contains("_")) {
            //获取_的为位置
            int i = oldString.indexOf("_");
            //获取_后面的位置
            int j = i + 1;
            char c = oldString.charAt(j);
            //将_后面的第一个字母改成大写
            String newLetter = String.valueOf(c).toUpperCase();
            String oldLetter = "_" + c;
            //删除_，返回内容
            newString = oldString.replace(oldLetter, newLetter);
        }
        return newString;
    }

    @Test
    public void testStringToMap() {

        String data = "{\"Message\":\"触发天级流控Permits:10\",\"RequestId\":\"54726717-6D76-5527-8B12-B869B2A29AC1\",\"Code\":\"isv.BUSINESS_LIMIT_CONTROL\"}";
        System.out.println(data);
    }
    @Test
    public void testList(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println(calendar.get(Calendar.DATE));
    }

    @Test
    public void testReplaceChar() {
        String startTime = "2022-09-29";
        System.out.println(startTime.replace("-", ""));
    }
    @Test
    public void testRandom(){
        Location location = new Location();
        location.setLat(new BigDecimal("35.548172"));
        location.setLon(new BigDecimal("118.445076"));

        Location location1 = new Location();
        location1.setLat(new BigDecimal("35.542044"));
        location1.setLon(new BigDecimal("118.443682"));

        Location location2 = new Location();
        location2.setLat(new BigDecimal("35.527495"));
        location2.setLon(new BigDecimal("118.443408"));

        Location location3 = new Location();
        location3.setLat(new BigDecimal("35.521885"));
        location3.setLon(new BigDecimal("118.444255"));

        Location location4 = new Location();
        location4.setLat(new BigDecimal("35.521556"));
        location4.setLon(new BigDecimal("118.446847"));

        List<Location> locationList = new ArrayList<>();
        locationList.add(location);
        locationList.add(location1);
        locationList.add(location2);
        locationList.add(location3);
        locationList.add(location4);
        double v = calculatePolygonArea(locationList);
        System.out.println(v);
    }

    /**
     * 球面积计算公式
     * @param locationList
     * @return
     */
    public static double calculatePolygonArea(List<Location> locationList) {
        double area = 0;
        int size = locationList.size();
        if (size > 2) {
            double LowX = 0.0;
            double LowY = 0.0;
            double MiddleX = 0.0;
            double MiddleY = 0.0;
            double HighX = 0.0;
            double HighY = 0.0;

            double AM = 0.0;
            double BM = 0.0;
            double CM = 0.0;

            double AL = 0.0;
            double BL = 0.0;
            double CL = 0.0;

            double AH = 0.0;
            double BH = 0.0;
            double CH = 0.0;

            double CoefficientL = 0.0;
            double CoefficientH = 0.0;

            double ALtangent = 0.0;
            double BLtangent = 0.0;
            double CLtangent = 0.0;

            double AHtangent = 0.0;
            double BHtangent = 0.0;
            double CHtangent = 0.0;

            double ANormalLine = 0.0;
            double BNormalLine = 0.0;
            double CNormalLine = 0.0;

            double OrientationValue = 0.0;

            double AngleCos = 0.0;

            double Sum1 = 0.0;
            double Sum2 = 0.0;
            double Count2 = 0;
            double Count1 = 0;

            double Sum = 0.0;
            double Radius = 6378000;

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    LowX = locationList.get(size-1).getLon().doubleValue() * Math.PI / 180;
                    LowY = locationList.get(size-1).getLat().doubleValue() * Math.PI / 180;
                    MiddleX =locationList.get(0).getLon().doubleValue() * Math.PI / 180;
                    MiddleY = locationList.get(0).getLat().doubleValue() * Math.PI / 180;
                    HighX = locationList.get(1).getLon().doubleValue() * Math.PI / 180;
                    HighY = locationList.get(1).getLat().doubleValue() * Math.PI / 180;
                } else if (i == size - 1) {
                    LowX = locationList.get(size-2).getLon().doubleValue() * Math.PI / 180;
                    LowY = locationList.get(size-2).getLat().doubleValue() * Math.PI / 180;
                    MiddleX =locationList.get(size-1).getLon().doubleValue() * Math.PI / 180;
                    MiddleY = locationList.get(size-1).getLat().doubleValue() * Math.PI / 180;
                    HighX = locationList.get(0).getLon().doubleValue() * Math.PI / 180;
                    HighY = locationList.get(0).getLat().doubleValue() * Math.PI / 180;
                } else {
                    LowX = locationList.get(i-1).getLon().doubleValue() * Math.PI / 180;
                    LowY = locationList.get(i-1).getLat().doubleValue() * Math.PI / 180;
                    MiddleX = locationList.get(i).getLon().doubleValue() * Math.PI / 180;
                    MiddleY = locationList.get(i).getLat().doubleValue() * Math.PI / 180;
                    HighX = locationList.get(i+1).getLon().doubleValue() * Math.PI / 180;
                    HighY = locationList.get(i+1).getLat().doubleValue() * Math.PI / 180;
                }

                AM = Math.cos(MiddleY) * Math.cos(MiddleX);
                BM = Math.cos(MiddleY) * Math.sin(MiddleX);
                CM = Math.sin(MiddleY);
                AL = Math.cos(LowY) * Math.cos(LowX);
                BL = Math.cos(LowY) * Math.sin(LowX);
                CL = Math.sin(LowY);
                AH = Math.cos(HighY) * Math.cos(HighX);
                BH = Math.cos(HighY) * Math.sin(HighX);
                CH = Math.sin(HighY);

                CoefficientL = (AM * AM + BM * BM + CM * CM) / (AM * AL + BM * BL + CM * CL);
                CoefficientH = (AM * AM + BM * BM + CM * CM) / (AM * AH + BM * BH + CM * CH);

                ALtangent = CoefficientL * AL - AM;
                BLtangent = CoefficientL * BL - BM;
                CLtangent = CoefficientL * CL - CM;
                AHtangent = CoefficientH * AH - AM;
                BHtangent = CoefficientH * BH - BM;
                CHtangent = CoefficientH * CH - CM;

                AngleCos = (AHtangent * ALtangent + BHtangent * BLtangent + CHtangent * CLtangent) / (
                        Math.sqrt(AHtangent * AHtangent + BHtangent * BHtangent + CHtangent * CHtangent)
                                * Math.sqrt(ALtangent * ALtangent + BLtangent * BLtangent
                                + CLtangent * CLtangent));

                AngleCos = Math.acos(AngleCos);

                ANormalLine = BHtangent * CLtangent - CHtangent * BLtangent;
                BNormalLine = 0 - (AHtangent * CLtangent - CHtangent * ALtangent);
                CNormalLine = AHtangent * BLtangent - BHtangent * ALtangent;

                if (AM != 0) {
                    OrientationValue = ANormalLine / AM;
                } else if (BM != 0) {
                    OrientationValue = BNormalLine / BM;
                } else {
                    OrientationValue = CNormalLine / CM;
                }

                if (OrientationValue > 0) {
                    Sum1 += AngleCos;
                    Count1++;

                } else {
                    Sum2 += AngleCos;
                    Count2++;
                    //Sum +=2*Math.PI-AngleCos;
                }
            }
            if (Sum1 > Sum2) {
                Sum = Sum1 + (2 * Math.PI * Count2 - Sum2);
            } else {
                Sum = (2 * Math.PI * Count1 - Sum1) + Sum2;
            }
            //平方米
            area = (Sum - (size - 2) * Math.PI) * Radius * Radius;
        }
        return Math.abs(area);
    }
}
