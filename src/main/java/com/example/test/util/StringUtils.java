package com.example.test.util;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author chengw 2022/8/31
 */
public class StringUtils {

    /**
     * 手机号脱敏
     */
    String phoneNumber = "1523716".replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");

    public static Map<Object, Object> getMap(String mapString) {
        return null;
    }

    /**
     * 将下划线转成驼峰
     * @param oldString 下划线分隔单词
     * @return 驼峰
     */
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

    /**
     * 如果字符串是空或者长度为0则返回true
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
