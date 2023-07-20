package com.example.test.algorithm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @Author: chengw
 * @Date: 2023/7/19 下午6:16
 */
@SpringBootTest
public class BitMapAlgorithm {

    Random random = new Random();

    /**
     * 在 2.5 亿个整数中找出不重复的整数(Integer)。注意：内存不足以容纳这 2.5 亿个整数
     * 1. 分治法，将整数通过hash分到n个不同的文件中(n取决于内存大小)
     * 2. 使用bitmap方式，把一个Integer分成两个bit一组代表一个数字的状态（00:没有；01：一个；11：多个），
     *    这样一个Integer可以代表16个整数，而整数最大值为：2^32-1,则需要268435456个Integer类型的数字可以代表
     *    时间复杂度为:O(n),空间复杂度为O(1)（占有内存空间是1G）
     */
    @Test
    public void bitTest() throws Exception {
        String path = "/Users/chengw/myWorld/";
        //这个是整数的最大值
        int maxValue = 10000;
        //批量插入测试数据
        long num = 2500L;
        insertFile(num,path,maxValue);
        queryNoRepetition(path,maxValue);
    }

    /**
     * 指定文件，写入文件固定数量的整数，每个整数站一行
     * @param num 写入文件的整数数量
     * @param path 文件的路径
     */
    private void insertFile(long num,String path,int maxValue) throws IOException {
        File file = new File(path + "test.txt");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0; i < num; i++) {
            bufferedWriter.write(String.valueOf(random.nextInt(maxValue)));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * 查询文件中不重复的数字
     * @param path 文件路径
     */
    private void queryNoRepetition(String path,int maxValue) throws Exception {
        File file = new File(path + "test.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //根据整数最大值得到合适大小的位图
        BitSet bitMap = getBitMap(maxValue);
        setBitMap(bufferedReader,bitMap);
        //关闭流
        bufferedReader.close();
    }

    /**
     * 创建位图
     * @param maxValue 根据整数的最大值创建bitmap
     */
    private BitSet getBitMap(int maxValue) {
        //两个bit代表一个整数
        int size = maxValue << 1;
        return new BitSet(size);
    }

    /**
     * 读取文件中的数字，修改对应的bit值
     * @param bufferedReader 文件流
     * @param bitMap
     * @throws Exception
     */
    private void setBitMap(BufferedReader bufferedReader,BitSet bitMap) throws Exception {
        int currentNum;
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            currentNum = Integer.parseInt(str);
            //取到每一行的数字，将修改两个bit的状态
            //如果是currentNum = 0，修改第0位和第1位
            //如果是currentNum = 1，修改第2位和第3位
            //1 << currentNum
            int fromBit = currentNum << 1;
            if (bitMap.get(fromBit + 1)) { //如果不是重复数字，则标记成重复数字
                bitMap.set(fromBit);
            }
            bitMap.set(fromBit + 1); //第二个bit设置成1
        }
        int in = 0;
        while (in <= bitMap.size()) {
            if (!bitMap.get(in) && bitMap.get(in + 1)) {
                System.out.println("不重复的数字" + in/2);
            } else if (bitMap.get(in) && bitMap.get(in + 1)) {
                System.out.println("重复的数字" + in/2);
            }
            in += 2;
        }
    }

}
