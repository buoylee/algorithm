package cn.buoy.leetcode.dp;

public class Q64 {
    /**
     * 超簡單.
     * https://www.youtube.com/watch?v=G59JEvLWEfU
     * 和 62題 unique path 基本没差, 只是变成了 求 带权路径 最小(從 dp[j][i - 1] 和 dp[j - 1][i] 取較小的 和 當前格相加).
     */
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];
        //init 第一行 和 第一列
        dp[0][0] = grid[0][0];
        for (int i = 1; i < colLen; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int j = 1; j < rowLen; j++) {
            dp[j][0] = grid[j][0] + dp[j - 1][0];
        }
        //只有2类可能 当前格的 左格(dp[j - 1][i])/上格(dp[j][i - 1]), 取最小的可能 加上 当前格value 即可.
        for (int j = 1; j < rowLen; j++) {
            for (int i = 1; i < colLen; i++) {
                dp[j][i] = grid[j][i] + Math.min(dp[j][i - 1], dp[j - 1][i]);
            }
        }
        return dp[rowLen - 1][colLen - 1];
    }
}
