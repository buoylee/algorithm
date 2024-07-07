package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q155 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=oE8zkEBNxN8
     * 思路: 保存變量 min,
     * 當遇到比當前 min 更小值時, 先把 min 再 push 一次, 然後 push 當前值, 更新 min.
     * pop 同理, 如果 pop 出的數 就是 min, 那需要再 pop 一次, 然後 更新 min.
     */
    class MinStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();

        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if (stack.pop() == min) min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}


