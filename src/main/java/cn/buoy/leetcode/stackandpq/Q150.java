package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

/**
 * 简单, 視頻
 * https://www.youtube.com/watch?v=6FCyyrrtjp0
 * 思路: stack. 数字则入栈, 遇到运算符, 栈內弹出2个數运算, 再把结果入栈, 反复直到结束.
 */
public class Q150 {
    public int evalRPN(String[] tokens) {
        int num1, num2;
        Stack<Integer> stack = new Stack<Integer>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.add(stack.pop() + stack.pop());
            } else if (s.equals("/")) {
                // 因爲 被除數 後pop出, 所以要暫存. 同理除法.
                num2 = stack.pop();
                num1 = stack.pop();
                stack.add(num1 / num2);
            } else if (s.equals("*")) {
                stack.add(stack.pop() * stack.pop());
            } else if (s.equals("-")) {
                num2 = stack.pop();
                num1 = stack.pop();
                stack.add(num1 - num2);
            } else {
                stack.add(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
}
