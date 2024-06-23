package cn.buoy.leetcode.dfsandbfs;

import cn.buoy.leetcode.NestedInteger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q364 {
    /**
     * https://www.youtube.com/watch?v=GgSeKo7fRrE
     * 直接看視頻,
     * 思路: 只需要一次bfs, 例: 最終需要, [x3, [x2, [x1]]], 4倍總數 即(sum for all ele of list) * 4 減去 extra 即([x1, [x2, [x3]]]). 看代碼.
     * <p>
     * 思路2: 先做一次 dfs, 得出 depth, 再做一次常規 dfs/bfs 得到結果.
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        // 用於記錄當前層, 和統計總層.
        int depth = 1;
        // list 所有元素和(沒有*層數)
        int sum = 0;
        // 其實是339的解, 用的 bfs, 因爲我是先計算了 depth * sum, 所以 extra 是需要減去的數. result 剛好就是 (depth * sum) - extra.
        int extra = 0;

        // bfs
        while (!queue.isEmpty()) {
            // 關鍵: 同樣, 記錄'上一層'的元素個數.
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger curr = queue.poll();
                if (curr.isInteger()) {
                    // 關鍵
                    sum += curr.getInteger();
                    // 常規 bfs
                    extra += curr.getInteger() * depth;
                } else {
                    // 把'同層元素'放入 queue
                    queue.addAll(curr.getList());
                }
            }
            // 每結束一層
            depth++;
        }
        // 關鍵
        return depth * sum - extra;
    }
}

