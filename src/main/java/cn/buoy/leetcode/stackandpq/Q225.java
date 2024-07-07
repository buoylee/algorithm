package cn.buoy.leetcode.stackandpq;

import java.util.LinkedList;
import java.util.Queue;

public class Q225 {
    /**
     * 確實有点搞笑, 應用場景未知. 簡單, 視頻
     * https://www.youtube.com/watch?v=bGlv0VGHed8
     * 思路: 當有新 node push 時, 將原有的 node 逐一 poll 出並 push, 直到 i < queue.size() - 1
     */
    class MyStack {

        Queue<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            queue = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        //每次push都重新翻转 size - 1 次
        public void push(int x) {
            queue.add(x);
            // 關鍵: i < queue.size() - 1
            for (int i = 0; i < queue.size() - 1; i++)
                queue.add(queue.poll());
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return queue.remove();

        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue.peek();

        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty();

        }
    }
}

