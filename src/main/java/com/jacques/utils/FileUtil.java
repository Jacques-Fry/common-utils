package com.jacques.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件工具
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @info 文件工具
 * @date 2020/12/30 9:47
 */
public class FileUtil {

    /**
     * 创建文件
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 13:20
     * @param sourceFile 源文件
     * @param filePath 新文件路径
     */
    public void createFile(File sourceFile,String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            // 创建文件夹
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            try {
                // 创建文件
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
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            // 删除文件
            file.delete();
        }
    }

    /**
     * 获取文件名
     *
     * @author: Jacques Fry
     * @date: 2020/12/31 9:27
     * @param fileType 文件类型
     * @return java.lang.String
     */
    public String getFileName(String fileType){
        return UUID.randomUUID().toString().replaceAll("-", "") + "."+fileType;
    }


}
