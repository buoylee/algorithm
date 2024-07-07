package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

/**
 * 簡單, 視頻, 代碼.
 * https://leetcode.com/problems/basic-calculator/description/
 * https://www.youtube.com/watch?v=ijyUwyt5vkU 速度看
 * 思路: stack 的用處就是 cache 當前 '沒輪到它處理' 的數據.
 * 當出現 '(', 需要把 之前的 數和符號 都 stack.push,
 * 當出現 digit, result += sign * whole(digit)
 * 當出現 ')', result = result * pop1 + pop2
 */
public class Q224 {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        //正负号
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                result += num * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;
            } else if (s.charAt(i) == '-') {
                sign = -1;
            } else if (s.charAt(i) == '(') {
                //将前边的 數字/sum/符号 压栈
                //这里不用重置number, 前边一定不是数字
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                // reset the sign and result for the value in the parenthesis
                // 細節: 前邊的符號已 cache, 這裏給 ()內 清空符號.
                sign = 1;
                // 細節: 記得 清空, 給 '()' 內 計算讓出空間.
                result = 0;
            } else if (s.charAt(i) == ')') {
                // '()'外的 sign
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                // '()'外的 +/- num
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis
            }
        }
        return result;
    }
}
