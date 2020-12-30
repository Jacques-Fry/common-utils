package com.jacques.utils.function;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author Jacques·Fry
 * @version 1.0
 * @info 公式if函数
 * @date 2020/9/3 13:50
 */
public class IF extends PostfixMathCommand {

    public IF() {
        //初始化参数格式（如参数个数为变长，则为-1）
        super.numberOfParameters = -1;
    }

    @Override
    public void run(Stack stack) throws ParseException {
        //检查栈
        this.checkStack(stack);
        //出栈
        List<Object> paramList = new ArrayList<Object>(3);
        for (int i = 0; i < 3; i++) {
            if (!stack.empty()) {
                paramList.add(stack.pop());
            }
        }
        Collections.reverse(paramList);
        //取值
        Object param1 = paramList.get(0);
        Object param2 = paramList.get(1);
        Object param3 = null;
        if (paramList.size() > 2) {
            param3 = paramList.get(2);
        }
        //计算结果入栈
        stack.push(this.method(param1, param2, param3));
    }

    private Object method(Object param1, Object param2, Object param3) throws ParseException {
        if ((param1 instanceof Number) && (param2 instanceof Number)) {
            double p1 = ((Number) param1).doubleValue();
            double p2 = ((Number) param2).doubleValue();
            if (p1 == 0.0) {
                //条件为false 提供的值
                if (param3 instanceof Number) {
                    return ((Number) param3).doubleValue();
                }
                //默认值
                return p1;
            }
            //条件为true的值
            return p2;
        } else {
            throw new ParseException("Invalid parameter type");
        }
    }
}
