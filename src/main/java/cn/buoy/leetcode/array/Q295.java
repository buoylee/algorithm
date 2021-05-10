package cn.buoy.leetcode.array;

import java.util.PriorityQueue;

public class Q295 {
    /**
     * https://www.youtube.com/watch?v=cqhED6Xgy9Y
     * 思路 用 2个pq, 平均放置元素.
     * 实现: 保证 左边.len == 左边.len 或 左边.len+1 == 左边.len; 答案就是 (左最大 + 右最小)/2 或 左最大.
     */
    class MedianFinder {
        // 思路：xxxxX yYYY，如果你能够把data stream排成左边这种形式，小于X的都放在X左边，大于y的都放在y的右边，且X左边的数量==y右边数量+1（或数量），那么X就是其中位数。这就很明显X及其左边一个maxHeap，y及其右边使用一个minHeap
        // 并且还要保证X和y是相邻的大小，试想：如果Xy不是相邻，那么上面的结论不能成立。怎么保证相邻？需要规定coming data的放置规则
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

        }

        public void addNum(int num) {
            // coming data的放置规则:
            if (maxHeap.isEmpty() || num < maxHeap.peek()) maxHeap.add(num);
            else minHeap.add(num);

            if (maxHeap.size() == minHeap.size() + 2) {
                minHeap.offer(maxHeap.poll());
            }
            if (minHeap.size() == maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else return (double) maxHeap.peek();
        }
    }
}
