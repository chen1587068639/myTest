package com.example.test.util;
import java.io.*;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 * 文件工具类
 * @Author: chengw
 * @Date: 2023/1/10 上午11:16
 */
public class FileUtils {

    /**
     * 将pdf文件转换为Base64编码
     * @param file pdf文件
     * @return
     */
    public static String PdfToBase64(File file) {
        Encoder encoder = Base64.getEncoder();
        FileInputStream fin =null;
        BufferedInputStream bin =null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout =null;
        try {
            fin = new FileInputStream(file);
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return encoder.encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                assert fin != null;
                fin.close();
                assert bin != null;
                bin.close();
                assert bout != null;
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) {

        String fileName = "example.txt";
        String[] linesArray = new String[10]; //数组大小可以根据实际情况进行调整

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                linesArray[i] = line;
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 打印数组中的每一行
        for (String line : linesArray) {
            System.out.println(line);
        }
    }

}
