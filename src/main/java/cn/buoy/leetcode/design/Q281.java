package cn.buoy.leetcode.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q281 {
    /**
     * 簡單, 快速視頻, 代碼
     * https://www.youtube.com/watch?v=kjGqGzRaw3Y
     * 思路: 關鍵在如何把 2個 Iterator 輪流使用.
     * 2 Iterator push queue; next() 弹出 Iterator, 如果 Iterator 还有 next, 再 push queue.
     */
    public class ZigzagIterator {
        private Queue<Iterator<Integer>> queue;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            queue = new LinkedList<>();
            if (!v1.isEmpty()) queue.offer(v1.iterator());
            if (!v2.isEmpty()) queue.offer(v2.iterator());
        }

        public int next() {
            Iterator<Integer> iter = queue.poll();
            int next = iter.next();
            if (iter.hasNext()) queue.offer(iter);
            return next;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}
