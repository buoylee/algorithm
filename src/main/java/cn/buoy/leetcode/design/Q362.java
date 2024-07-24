package cn.buoy.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

public class Q362 {
    /**
     * 簡單, 類似 359, 視頻
     * https://www.youtube.com/watch?v=z9usYLTBKjY 失效
     * https://www.youtube.com/watch?v=Vw6IxrCd9Lk
     * 思路: 都差不多, 2種寫法.
     * 1. 存 "本 hit 時間", 計算 "當前 timestamp" - "hit time" >= 300, 就 pop
     * 2. 或, 存 "過期時間"("hit time" + 300) 都可以, "過期時間" <= "當前 timestamp"
     */
    public class HitCounter {
        Queue<Integer> queue = null;

        public HitCounter() {
            queue = new LinkedList<>();
        }

        public void hit(int timestamp) {
            queue.offer(timestamp);
        }

        public int getHits(int timestamp) {
            // 關鍵: "當前 timestamp" - "hit time" >= 300, 就 pop
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300)
                queue.poll();
            return queue.size();
        }
    }

    public class HitCounter2 {
        private int[] times;
        private int[] hits;

        /**
         * Initialize your data structure here.
         */
        public HitCounter2() {
            times = new int[300];
            hits = new int[300];
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                hits[index] = 1;
            } else {
                hits[index]++;
            }
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            int total = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    total += hits[i];
                }
            }
            return total;
        }
    }


}
