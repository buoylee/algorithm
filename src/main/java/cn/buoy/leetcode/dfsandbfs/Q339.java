package cn.buoy.leetcode.dfsandbfs;

import cn.buoy.leetcode.NestedInteger;

import java.util.List;

/**
 * https://www.youtube.com/watch?v=_Uc55RZiuII
 * 簡單看下視頻.
 * 思路: bfs 和 dfs 都差不多(bfs可能更直觀), 一層數組是一deep, 代碼簡單.
 */
public class Q339 {
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    private int helper(List<NestedInteger> list, int depth) {
        int result = 0;
        // 關鍵: DFS, 使得 都會先完成'下層的運算'才會完成'當前層的運算'.
        for (NestedInteger ele : list) {
            if (ele.isInteger()) {
                result += ele.getInteger() * depth;
            } else {
                result += helper(ele.getList(), depth + 1); // ++depth
            }
        }
        return result;
    }
}