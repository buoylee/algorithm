package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q227 {
    /**
     * 簡單, 視頻, 代碼.
     * 基本和 224 一樣. 只是 stack pushing 要在 運算符 判斷.
     * https://www.youtube.com/watch?v=ABMLLVzf4ZQ
     * 思路: '+/-' 全部 push, '乘/除' 先 pop 一個 運算完再 push, 最後 sum stack 中所有的元素.
     */
    public int calculate(String s) {
        int len = s.length();
        if (s == null || len == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        // 當前 摳出來的數
        int num = 0;
        char sign = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                // 看下一個是不是 digit
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
            }
            // 關鍵: 判斷運算符是 '+/-' 還是 '*//',
            // '*//' 可以先運算再 stack push
            // '+/-' 則直接 stack push
            // 記得更新 sign
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) { // 不是数字且空格, 或 末尾
                if (sign == '-')
                    stack.push(-num);
                if (sign == '+')
                    stack.push(num);
                //先完成 *, / 运算, 遇到则弹出数字运算, 后push
                if (sign == '*')
                    stack.push(stack.pop() * num);
                if (sign == '/')
                    stack.push(stack.pop() / num);
                // 下一個數字的 sign
                sign = s.charAt(i);
                num = 0;
            }
        }
        for (int i : stack)
            result += i;
        return result;
    }

    public static void main(String[] args) {
        String s = "1+2*3";
        Q227 q227 = new Q227();
        q227.calculate(s);
    }
}
