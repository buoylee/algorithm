package cn.buoy.leetcode.dp;

public class Q64 {
    /**
     * 参考思路, 写法不同: https://www.youtube.com/watch?v=G59JEvLWEfU
     * 和 unique path 基本没差, 只是变成了 求 带权路径 最小.
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        //init 第一行第一列
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int j = 1; j < m; j++) {
            dp[j][0] = grid[j][0] + dp[j - 1][0];
        }
        //只有2类可能 当前格的 左格/上格, 去最小的可能 加上 当前格value 即可.
        for (int j = 1; j < m; j++) {
            for (int i = 1; i < n; i++) {
                dp[j][i] = grid[j][i] + Math.min(dp[j][i - 1], dp[j - 1][i]);
            }
        }
        return dp[m - 1][n - 1];
    }
}
