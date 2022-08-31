package com.example.test.util;

import java.text.SimpleDateFormat;

/**
 * @author chengw 2022/8/31
 */
public class StringUtils {

    /**
     * 手机号脱敏
     */
    String   phoneNumber = "1523716".replaceAll("(\\w{3})\\w*(\\w{4})", "$1****$2");
}
