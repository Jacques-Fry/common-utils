package com.jacques.utils;

import java.util.Map;

/**
 * 自定义模板转换工具
 *
 * @author Jacques·Fry
 * @version 1.0.0
 * @info 自定义模板转换工具
 * @date 2020/12/30 10:55
 */
public class TemplateStrUtils {
    /**
     * 表达式以...开头
     */
    private final static String START_STR = "{{";
    /**
     * 表达式以...结尾
     */
    private final static String END_STR = "}}";


    /**
     * 微信模板的属性转换[简易]
     */
    public static String convertWeChatContent(String template, String... attrs) {
        //微信模板参数表达式前缀
        String startStr = "{{";
        //微信模板参数表达式后缀
        String endStr = "}}";
        //微信模板参数表达式匹配
        String regexStr = "\\{\\{(.*?)+\\}\\}";
        String result = template;
        int endIndex = 0;
        for (String attr : attrs) {
            //模板解析
            endIndex = template.indexOf(endStr);
            //参数替换
            if (attr == null) {
                attr = "";
            }
            result = result.replaceFirst(regexStr, attr);
            template = template.substring(endIndex + endStr.length());
        }
        //未替换的参数全部替换为空字符
        result = result.replaceAll(regexStr, "");
        return result;

    }

    /**
     * 模板属性值转换
     *
     * @param paramMap    转换数据
     * @param template    需要转换的模板字符串
     * @param paramEndStr 参数名的后缀
     * @return 转换后的模板字符串
     */
    public static String convertContent(String template, Map<String, String> paramMap, String paramEndStr) {
        //微信模板参数表达式前缀
        String startStr = START_STR;
        //微信模板参数表达式后缀
        String endStr = END_STR;

        String result = template;
        int start = 0;
        int end = 0;
        while (template.contains(startStr)) {
            //获取模板参数
            start = template.indexOf(startStr);
            end = template.indexOf(endStr);
            String paramKey = template.substring(start + startStr.length(), end);
            template = template.substring(end + endStr.length());
            //给模板参数赋值
            String data = paramMap.get(paramKey + paramEndStr);
            if(data==null){
                data="";
            }
            result = result.replace(startStr + paramKey + endStr, data);
        }
        return result;
    }
}
