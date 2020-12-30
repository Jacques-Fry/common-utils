package com.jacques.utils;

import java.text.DecimalFormat;

/**
 * 数据格式化工具
 *
 * @info 数据格式化工具
 * @version 1.0.0
 * @author Jacques·Fry
 * @date 2020/12/30 10:14
 */
public class DataFormatUtils {

    private static DataFormatUtils INSTANCE;

    private DataFormatUtils() {
        // 单例模式
    }

    public static DataFormatUtils getInstance() {
        // 双重锁
        if (INSTANCE == null) {
            synchronized (DataFormatUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataFormatUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * float类型保留位数小数
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:16
     * @param num 传入的数据
     * @param length 保留位数
     * @return java.lang.String
     */
    public static String keepToDecimals(float num, int length) {
        if(num<1){
            return String.valueOf(num);
        }
        DecimalFormat decimalFormat = getDecimalFormat(length);
        return decimalFormat.format(num);
    }

    /**
     * double类型保留位数小数
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:17
     * @param num 传入的数据
     * @param length 保留位数
     * @return java.lang.String
     */
    public static String keepToDecimals(double num, int length) {
        if(num<1){
            return String.valueOf(num);
        }
        DecimalFormat decimalFormat = getDecimalFormat(length);
        return decimalFormat.format(num);
    }

    /**
     * 保留位数
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:18
     * @param length 保留长度
     * @return java.text.DecimalFormat
     */
    private static DecimalFormat getDecimalFormat(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        stringBuilder.append(".");
        for (int i = 0; i < length; i++) {
            stringBuilder.append("0");
        }
        return new DecimalFormat(stringBuilder.toString());
    }

}
