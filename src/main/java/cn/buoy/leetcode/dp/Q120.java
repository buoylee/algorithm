package cn.buoy.leetcode.dp;

import java.util.List;

public class Q120 {
    /**
     * 簡單.
     * https://www.youtube.com/watch?v=0qenZd4G4iI
     * 思路: 实际上用了 dp, 只是在原 triangle 中做 dp 操作. 從下到上, 計算出上一行的所有元素(i) 和 "下层邻居(i 和 i+1)" 的最小 sum, 到頂就是答案.
     * 爲什麼不由上到下, 因爲底邊元素有多個, 到最后一层会产生多个 sum, 還需要 多一次遍歷 才得出最小的值, 而頂點不再需要遍歷.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 關鍵: 從 倒數第2行開始 遍歷.
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> upperRow = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                // 下层的 j 和 j+1, 因为 upper 的 dp[j] = 他们2者中的较小值 + upper[j].
                int lowerRow1 = triangle.get(i + 1).get(j);
                int lowerRow2 = triangle.get(i + 1).get(j + 1);
                // 直接把 dp 的值放到本行 j 中.
                upperRow.set(j, Math.min(lowerRow1, lowerRow2) + upperRow.get(j));
            }
        }
        return triangle.get(0).get(0);
    }


    // dfs
    public int minimumTotal2(List<List<Integer>> triangle) {
        int rows = triangle.size(), cols = triangle.get(rows - 1).size();
        int[][] memo = new int[rows][cols];
        memo[0][0] = dfs(triangle, 0, 0, memo);
        return memo[0][0];
    }

    /**
     * @param triangle
     * @param row      遍历到第几行
     * @param col      该行的第几列
     * @param memo
     * @return
     */
    int dfs(List<List<Integer>> triangle, int row, int col, int[][] memo) {
        //走到最后一行, 返回选中的元素
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }
        if (memo[row][col] != 0) return memo[row][col];
        int minsum = Integer.MAX_VALUE;
        //DFS思路是: 从上往下找路, 但是从下往上加, 上一层找下一层的最小值, 然后sum, 当做本节点的最小值(dp).
        minsum = Math.min(minsum, dfs(triangle, row + 1, col, memo));
        minsum = Math.min(minsum, dfs(triangle, row + 1, col + 1, memo));
        memo[row][col] = minsum + triangle.get(row).get(col);
        return memo[row][col];
    }
}
