package cn.buoy.leetcode.dp;

import java.util.List;

public class Q120 {
    /**
     * https://www.youtube.com/watch?v=0qenZd4G4iI
     * 思路相同, 不过用的迭代.
     */
    public int minimumTotal(List<List<Integer>> triangle) {
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
