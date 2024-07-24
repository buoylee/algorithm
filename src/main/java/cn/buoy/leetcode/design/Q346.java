package cn.buoy.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

public class Q346 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=PnpRkJqPzxk
     * 思路: link 放 ele, len > 容量 就 pop head, 再算 average
     */
    public class MovingAverage {
        private int capacity; // 限制容量
        private Queue<Integer> queue;
        private double sum; // link 的所有 ele 的 sum

        public MovingAverage(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedList<>();
            this.sum = 0.0;
        }

        public double next(int val) {
            // 等於容量了, 就pop head, 並 sum 減去 value.
            if (queue.size() == capacity)
                sum -= queue.poll();
            // 加入 value
            queue.offer(val);
            // 算 average
            sum += val;
            return sum / queue.size();
        }
    }
}
