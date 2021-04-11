package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=6FCyyrrtjp0
 * 简单.
 * 数字则入栈, 遇到运算符, 弹出2个运算, 在把结果入栈, 反复直到结束.
 */
public class Q150 {
    public int evalRPN(String[] tokens) {
        int a, b;
        Stack<Integer> S = new Stack<Integer>();
        for (String s : tokens) {
            if (s.equals("+")) {
                S.add(S.pop() + S.pop());
            } else if (s.equals("/")) {
                b = S.pop();
                a = S.pop();
                S.add(a / b);
            } else if (s.equals("*")) {
                S.add(S.pop() * S.pop());
            } else if (s.equals("-")) {
                b = S.pop();
                a = S.pop();
                S.add(a - b);
            } else {
                S.add(Integer.parseInt(s));
            }
        }
        return S.pop();
    }
}
