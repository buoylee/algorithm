package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q224 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        //正负号
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
            } else if (c == '+') {
                //带上前边的正负号
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                //下一个数字, 括号, 将 需要 -1 * sign.
                sign = -1;
            } else if (c == '(') {
                //将 前边的结果, 符号 压栈
                //这里不用重置number, 前边一定不是数字
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                // +, -, 完成括号里和符号运行
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                //在弹出上一层的 数字 类加上.
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if (number != 0)
            result += sign * number;
        return result;
    }
}
