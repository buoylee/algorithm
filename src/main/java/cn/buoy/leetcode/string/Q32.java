package cn.buoy.leetcode.string;

import java.util.Stack;

public class Q32 {
    /**
     * https://www.youtube.com/watch?v=M1Vw5Tk1rw4
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int left = -1;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '(') stack.push(j);
            else {
                if (stack.isEmpty()) left = j;
                else {
                    stack.pop();
                    if (stack.isEmpty()) max = Math.max(max, j - left);
                    else max = Math.max(max, j - stack.peek());
                }
            }
        }
        return max;
    }
}
