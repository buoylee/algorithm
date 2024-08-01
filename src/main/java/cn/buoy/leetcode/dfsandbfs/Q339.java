package cn.buoy.leetcode.dfsandbfs;

import cn.buoy.leetcode.NestedInteger;

import java.util.List;

public class Q339 {
    /**
     * 簡單看下視頻. bfs 和 dfs 都差不多(bfs可能更直觀)
     * https://www.youtube.com/watch?v=_Uc55RZiuII
     * 思路: 每次 dfs, n + 1 到下层,
     * 是 list 就 dfs, 路过 是 int 就 * n, 然后 +=, 从最下层 累加, 一直返回到顶层.
     */
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> list, int depth) {
        int result = 0;
        // 關鍵: dfs, 使得 都會先完成'下層的運算'才會完成'當前層的運算'.
        for (NestedInteger ele : list) {
            if (ele.isInteger()) {
                result += ele.getInteger() * depth;
            } else
                result += dfs(ele.getList(), depth + 1); // ++depth
        }
        return result;
    }
}