package cn.buoy.leetcode.array;

import java.util.PriorityQueue;

public class Q295 {
    /**
     * 簡單, 直到思路的話, 視頻.
     * https://www.youtube.com/watch?v=cqhED6Xgy9Y
     * 思路: 用 2个pq, 所有數 平均放置 在2堆裏, 左堆爲最大堆, 右堆最小堆. 保证 左堆.len == 左堆.len 或 左堆.len+1 == 左堆.len; 偶數是 (左堆最大 + 右堆最小)/2; 奇數是 左堆最大.
     */
    class MedianFinder {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        public void addNum(int num) {
            // coming data的放置规则:
            // 關鍵: 這裏應該不是一定必須這麼寫的把? 分配的第一步, 保證 左堆 != null, 且 大於 "左堆max" 就放到 右堆.
            if (maxHeap.isEmpty() || num < maxHeap.peek())
                maxHeap.add(num);
            else
                minHeap.add(num);
            // 關鍵: 爲了保證 left堆 = right堆 + 1
            // 當發現 left堆 超過時:
            if (maxHeap.size() == minHeap.size() + 2)
                minHeap.offer(maxHeap.poll());
            // 當發現 right堆 超過時:
            if (minHeap.size() == maxHeap.size() + 1)
                maxHeap.offer(minHeap.poll());
        }

        // 求中位數. 偶數: (左堆最大 + 右堆最小)/2; 奇數: 左堆最大.
        public double findMedian() {
            if (maxHeap.size() == minHeap.size())
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else
                return maxHeap.peek();
        }
    }
}
