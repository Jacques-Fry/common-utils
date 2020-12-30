package com.jacques.utils.function;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * @author Jacques·Fry
 * @version 1.0
 * @info 取最小
 * @date 2020/9/3 18:42
 */
public class MIN extends PostfixMathCommand {
    public MIN() {
        super();
        // 使用参数的数量
        numberOfParameters = 2;
    }

    @Override
    public void run(Stack inStack) throws ParseException {
        //检查栈
        checkStack(inStack);
        Object param2 = inStack.pop();
        Object param1 = inStack.pop();

        if ((param1 instanceof Number) && (param2 instanceof Number)) {
            double p1 = ((Number) param2).doubleValue();
            double p2 = ((Number) param1).doubleValue();

            double result = Math.min(p1, p2);

            inStack.push(new Double(result));
        } else {
            throw new ParseException("Invalid parameter type");
        }
        return;
    }

}