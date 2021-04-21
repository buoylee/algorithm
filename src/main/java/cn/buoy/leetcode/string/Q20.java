package cn.buoy.leetcode.string;

import java.util.Stack;

public class Q20 {
    /**
     * 简单
     * https://www.youtube.com/watch?v=2NFQd-jyX48
     *
     * @param s
     * @return
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
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        //遍历完, 如果还有剩, 不匹配.
        return stack.isEmpty();
    }
}
