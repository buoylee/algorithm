package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q232 {
    /**
     * 太简单, 2stack
     * https://www.youtube.com/watch?v=G-xV4m6G2C8
     */
    class MyQueue {

        Stack<Integer> input = new Stack();
        Stack<Integer> output = new Stack();

        public void push(int x) {
            input.push(x);
        }

        public Integer pop() {
            peek();
            return output.pop();
        }

        //如果中途有新元素进来, 先存在input, 只有等output都空了, 才能再往output里放
        public int peek() {
            if (output.empty())
                while (!input.empty())
                    output.push(input.pop());
            return output.peek();
        }

        public boolean empty() {
            return input.empty() && output.empty();
        }
    }
}

