package com.example.test.algorithm;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: chengw
 * @Date: 2023/7/18 下午5:26
 */
@SpringBootTest
public class FileAlgorithm {

    public final static String str = "abcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwzabcdefghijklmnopqrstuvwxwz";
    /**
     * 找到大文件中的高频次单词
     */
    @Test
    public void findHighWord() throws Exception {
        synchronized (Object.class) {

        }
        //大文件文件路径
        String path = "/Users/chengw/myWorld/test.txt";
//        //开始填入一些测试数据
//        FileWriter fileWriter = new FileWriter(new File(path));
//        //outPutStream通道写入完毕后关闭
//        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//        for (int i = 0; i < 100000; i++) {
//            int r1 = (int) (Math.random() * str.length());
//            int r2 = (int) (Math.random() * str.length());
//            String word = str.substring(Math.min(r1,r2), Math.max(r1,r2));
//            bufferedWriter.write(word);
//            bufferedWriter.newLine();
//        }
//        //关闭流
//        bufferedWriter.flush();
//        bufferedWriter.close();
//        //填入一些测试数据结束
//        insertFile(path);
        countHighWord(path);
        System.out.println(Math.random() * 100);
    }

    /**
     * 每个单词通过hash均匀的分配到1000个文件中
     * @param path 大文件路径
     */
    private void insertFile(String path) throws Exception {
        File file = new File(path);
        Reader reader = new FileReader(file);
        BufferedReader buffer = new BufferedReader(reader);
        //创建一千个文件
        for (int i = 0; i < 1000; i++) {
            File createFile = new File("/Users/chengw/myWorld/test/" + i + ".txt");
            boolean flag = createFile.createNewFile();
        }
        String word;
        while ((word = buffer.readLine()) != null) {
            int hash = Math.abs(word.hashCode() % 1000);
            File currentFile = new File("/Users/chengw/myWorld/test/" + hash + ".txt");
            Writer fileWriter = new FileWriter(currentFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            //指定文件写入单词，然后换行
            bufferedWriter.newLine();
            bufferedWriter.write(word);
            //刷新并关闭outPutStream通道
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    /**
     * 计算大文件中的高频词前一百个和出现次数
     * @param path
     */
    private void countHighWord(String path) throws Exception {
        //存储前一百个单词:小根堆：下面两者等效
//        PriorityQueue<Word> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.count));
        int count = 100;
        PriorityQueue<Map.Entry<String,Integer>> heap = new PriorityQueue<>(new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                return o1.getValue() - o2.getValue() ;
            }
        });
        for (int i = 0; i < 1000; i++) {
            //key:word;value:num
            Map<String,Integer> map = new HashMap<>();
            File file = new File("/Users/chengw/myWorld/test/" + i + ".txt");
            Reader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String word;
            while ((word = buffer.readLine()) != null) {
                if (map.containsKey(word)) {
                    map.put(word,(map.get(word)+1));
                } else {
                    map.put(word,1);
                }
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (heap.size() <= count) {
                    heap.offer(entry);
                } else if (entry.getValue() > heap.peek().getValue()){
                    heap.poll();
                    heap.offer(entry);
                }
            }
        }
        while (!heap.isEmpty()) {
            Map.Entry<String, Integer> poll = heap.poll();
            System.out.println("字符串" + poll.getKey() + "次数" + poll.getValue());
        }
    }

}
