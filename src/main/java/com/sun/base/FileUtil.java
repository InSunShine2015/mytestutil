package com.sun.base;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	/**
     * 随机读取文件内容
     */
    public void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
           System.out.println("随机读取一段文件内容：");
           // 打开一个随机访问文件流，按只读方式
           randomFile = new RandomAccessFile(fileName, "r");
           String lineStr = null;
           while ((lineStr = randomFile.readLine()) != null){
               String lineContent = new String(lineStr.getBytes("ISO-8859-1"),"UTF-8") ;
               System.out.println(lineContent);
               Thread.sleep(1000);
               System.out.println("################");
           }

        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
