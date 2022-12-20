package com.example.test.spring;

import com.example.test.util.ExcelUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;

/**
 * @Author: chengw
 * @Date: 2022/12/6 上午10:01
 */
@SpringBootTest
public class ClassTest {

    @Test
    public void newClass() throws Exception {
        //得到class
        Class<?> excelUtilClass = Class.forName("com.example.test.util.ExcelUtils");
        Constructor<?> declaredConstructor = excelUtilClass.getDeclaredConstructor();
        Object excelUtil = declaredConstructor.newInstance();
        //Class<ExcelUtils> excelUtilsClass = ExcelUtils.class;


    }
}
