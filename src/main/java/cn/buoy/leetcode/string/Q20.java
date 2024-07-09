package cn.buoy.leetcode.string;

import java.util.Stack;

public class Q20 {
    /**
     * 超简单, 視頻, 代碼
     * https://www.youtube.com/watch?v=2NFQd-jyX48
     * 思路: 括號就 stack, 注意匹配 正確類型 和 符號剩餘.
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        //塞反向符号, 如果遇到相同 在栈顶 ok, 不同 或者 stack空了, 不匹配.
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) // 缺少左半 || 括號類型不匹配.
                return false;
        }
        //遍历完, 多出左半邊, 失敗.
        return stack.isEmpty();
    }
}
