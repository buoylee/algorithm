package cn.buoy.leetcode.dfsandbfs;

import cn.buoy.leetcode.NestedInteger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q364 {
    /**
     * 339 相反
     * 简单, 視頻
     * https://www.youtube.com/watch?v=GgSeKo7fRrE 失效
     * https://www.youtube.com/watch?v=r6SwGjL8oAI&t=15s
     * 思路: 只需要一次bfs, 例: 最終需要, [elex3, [elex2, [elex1]]], 4倍總數 即(sum for all ele of list) * 4 減去 extra 即([x1, [x2, [x3]]]) 就是答案.
     * 思路2: 先做一次 dfs, 得出 depth, 再做一次常規 dfs/bfs 得到結果.
     */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);
        // 用於記錄當前層, 和統計總層.
        int depth = 1;
        // list 所有元素和
        int sum = 0;
        // 这里 extra 的计算, 其實就是339 bfs 解法, 因爲我是先計算了 depth * sum, 所以 extra 是需要減去的數. result 剛好就是 (totalDepth * sum) - extra.
        int extra = 0;
        while (!queue.isEmpty()) {
            // 關鍵: 同樣, 記錄'上一層'的元素個數.
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger curr = queue.poll();
                if (curr.isInteger()) {
                    // 關鍵: 先统计 所有 ele 的 sum, 等 找到最大 depth, 在相乘. 就是 我们要的 n倍總數
                    sum += curr.getInteger();
                    // 339 bfs 解法
                    extra += curr.getInteger() * depth;
                } else
                    // 把 '同層的 list' 放入 queue
                    queue.addAll(curr.getList());
            }
            // 每結束一層
            depth++;
        }
        // 4倍總數 即(sum for all ele of list) * 4 減去 extra 即([x1, [x2, [x3]]]) 就是答案.
        return depth * sum - extra;
    }
}

