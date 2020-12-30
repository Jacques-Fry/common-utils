package com.jacques.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @info 文件工具
 * @date 2020/12/30 9:47
 */
public class FileUtils {

    /**
     * 创建文件
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 13:20
     */
    @Test
    public void createFile() {
        String filePath = "D:/test/file";
        String fileName = "a.txt";
        File file = new File(filePath);
        if (file.mkdirs()) {
            System.out.println("创建创建文件夹");
        }
        file = new File(filePath, fileName);
        if (!file.exists()) {
            try {
                System.out.println("创建文件");
                file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 移动文件
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 13:20
     */
    public void moveFile() {

    }


    /**
     * 删除文件
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 13:20
     */
    @Test
    public void deleteFile() {
        String filePath = "D:/test/file";
        String fileName = "a.txt";
        File file = new File(filePath, fileName);
        if(file.exists()){
            System.out.println("删除文件");
            file.delete();
        }
    }

}
