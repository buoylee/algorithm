package cn.buoy.leetcode.stackandpq;

import java.util.LinkedList;
import java.util.Queue;

public class Q225 {
    /**
     * 有点搞笑
     * https://www.youtube.com/watch?v=bGlv0VGHed8
     */
    class MyStack {

        Queue<Integer> que;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            que = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        //每次push都从新翻转 size - 1 次
        public void push(int x) {
            que.add(x);
            for (int i = 0; i < que.size() - 1; i++) {
                que.add(que.poll());
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return que.remove();

        }

        /**
         * Get the top element.
         */
        public int top() {
            return que.peek();

        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return que.isEmpty();

        }
    }
}

