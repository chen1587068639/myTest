package com.example.test.algorithm.number;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Random;

/**
 * 文件中40亿个无符号int数字，利用4k，找出40亿个数字的中位数
 * @Author: chengw
 * @Date: 2023/7/26 上午9:56
 */
@SpringBootTest
public class IntAlgorithm {

    private static volatile Random random;
    static {
        if (random == null) {
            synchronized(Random.class) {
                if (random == null) {
                    random = new Random();
                }
            }
        }
    }

    @Test
    public void testInsert() throws IOException {
        File file = new File("/Users/chengw/myWorld/test.txt");
        boolean newFile = file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (int i = 0;i < 400000;i++) {
            bufferedWriter.write(String.valueOf(random.nextInt(Integer.MAX_VALUE)));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * 文件中40亿个无符号int数字，利用4k内存，找出40亿个数字的中位数
     * @throws Exception
     */
    @Test
    public void testMedian() throws Exception{
        int num = 400000;
        File file = new File("/Users/chengw/myWorld/test.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //最大字节
        int maxByte = 4000;
        //保留最高位1
        int length = Integer.highestOneBit(maxByte / 4);
        System.out.println(searchMedian("/Users/chengw/myWorld/test.txt",length,num));
        bufferedReader.close();
    }

    /**
     * 由于内存限制导致只能通过一个固定长度的数组求中位数
     * 每一个数组中的数字记录把一个Integer.MAX_VALUE / arr.length出现的频次
     * 计算前n个arr元素频次和大于numLength / 2 的时候，中位数就在arr[n - 1]中
     * 再次循环读取文件，在文件中取出(Integer.MAX_VALUE / arr.length) * n-1 到(Integer.MAX_VALUE / arr.length) * n区间的数字
     * 40亿数字
     * 第一次循环，
     * 数组长度为512，Integer.MAX_VALUE = 2,147,483,647，arr[0]计算文件数值大小在0-4194304的数字的频次和，依次到arr[arr.length - 1]计算文件数值大小在2,143,289,343-2,147,483,647的数字的频次和
     *
     * 中位数位置为20亿，数组中最小数字为0，最大数字为Integer.MAX_VALUE,每个元素计算
     * 假设arr[0] = 7812500,,依次到arr[arr.length - 1]都是7812500
     * 中位数在第arr[255],
     *
     * 直到计算出中位数为止
     * @param filePath 文件
     * @param length 数组长度
     * @param numLength 数字的数量
     */
    private int searchMedian(String filePath,int length,int numLength) throws Exception{
        //中位数
        int medianIndex = numLength / 2;
        //数组的最大数字
        int minNum = 0;
        int maxNum = Integer.MAX_VALUE;
        while (medianIndex > 0) {
            int[] arr = new int[length];

            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader111 = new BufferedReader(fileReader);
            //每个数组应该容纳(MaxNum - MinNum) / arr.length的数字
            int partSize = (maxNum - minNum) / arr.length;
            int arrSum = 0;
            String fileNum;
            while ((fileNum = bufferedReader111.readLine()) != null) {
                //当前读取的数字
                Integer currentNum = Integer.valueOf(fileNum);
                //数字应该添加到那一个位置
                if (currentNum >= minNum && currentNum <= maxNum) {
                    try {
                        arr[(currentNum - minNum) / partSize]++;
                        arrSum++;
                    } catch (Exception e) {
                        return -1;
                    }
                }
            }
            bufferedReader111.close();
            int sum = 0;
            //判断一下
            //计算中位数应该在数组的哪个索引中
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                if (arrSum <= 512) {
                    if ((arrSum >> 1) >= sum) {
                        return minNum + i * partSize;
                    }
                } else {
                    if (medianIndex <= sum) {
                        //下一轮分数据的起始位置，应该计算一下
//                    minNum = sum - arr[i];
                        minNum = minNum + i * partSize;
//                    maxNum = sum;
                        maxNum = minNum + (i + 1) * partSize;
                        //中位数在的位置
                        medianIndex = medianIndex - sum + arr[i];
                        System.out.println(medianIndex);
                        break;
                }

                }
            }
            System.out.println("结束第一层循环");
        }
        System.out.println((minNum + maxNum) /2);
        return -1;
    }
}
