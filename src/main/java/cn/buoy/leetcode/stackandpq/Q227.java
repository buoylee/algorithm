package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q227 {
    public static void main(String[] args) {
        String s = "1+2*3";
        Q227 q227 = new Q227();
        q227.calculate(s);
    }

    /**
     * 复习看稍微看一下
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            System.out.println(s.charAt(i));
            if (Character.isDigit(s.charAt(i))) {
//                System.out.println(num);
                num = num * 10 + s.charAt(i) - '0';
            }
            //不是数字且空格, 或 末尾
            //先入数字, 只有在当前
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '+') {
                    stack.push(num);
                }
                //先完成 *, / 运算, 遇到则弹出数字运算, 后push
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                //关键在这里, 每当遇到新的符号, 才会先检查前一个符号的运算, 运算结束的结果push入栈后, 才更新sign
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }
}
