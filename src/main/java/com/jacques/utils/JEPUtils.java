package com.jacques.utils;
import com.alibaba.fastjson.JSONObject;
import com.jacques.utils.function.IF;
import com.jacques.utils.function.MAX;
import com.jacques.utils.function.MIN;
import org.nfunk.jep.JEP;

import java.util.Set;

/**
 * JEP工具
 *
 * @author Jacques·Fry
 * @version 1.0
 * @info JEP工具
 * @date 2020/9/3 17:31
 */
public class JEPUtils {

    private static JEP jep;

    private static JEPUtils INSTANCE;

    private JEPUtils() {
        // 单例模式
        jep = new JEP();
        // 添加常用函数
        jep.addStandardFunctions();
        // 添加常用常量
        jep.addStandardConstants();
        // 添加自定义函数
        jep.addFunction("if", new IF());
        jep.addFunction("max", new MAX());
        jep.addFunction("min", new MIN());
    }

    public static JEPUtils getInstance() {
        // 双重锁
        if (INSTANCE == null) {
            synchronized (JEPUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new JEPUtils();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * 公式计算
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:30
     * @param formula 公式
     * @param jsonArr 计算参数
     * @return java.lang.Double
     */
    public Double calculate(String formula, JSONObject... jsonArr) {
        //加入已知参数
        for (JSONObject json : jsonArr) {
            this.addVariable(jep, json);
        }
        formula = formula.replace(" ", "");
        //执行
        jep.parseExpression(formula);
        if (jep.hasError()) {
            throw new RuntimeException("公式异常: " + jep.getErrorInfo());
        }
        Double value = jep.getValue();
        if (value.equals(Double.NaN)) {
            throw new RuntimeException("没有任何返回结果 计算公式可能有错");
        }
        return value;
    }

    /**
     * 混合参数
     *
     * @author: Jacques Fry
     * @date: 2020/12/30 10:31
     * @param jep
     * @param json 参数
     * @return void
     */
    public void addVariable(JEP jep, JSONObject json) {
        if (json != null) {
            Set<String> keySet = json.keySet();
            for (String key : keySet) {
                jep.addVariable(key, json.get(key));
            }
        }
    }
}
