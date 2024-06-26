package cn.buoy.leetcode.dp;

public class Q221 {
    /**
     * https://www.youtube.com/watch?v=5AfIa6z3Yw0
     * 思路: dp, 左, 左上, 上 3格中的最小值 + 1 就是本格dp. 要從很多1的例子中, 推敲出.
     */
    public int maximalSquare(char[][] arrays) {
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0)
            return 0;

        int max = 0, row = arrays.length, col = arrays[0].length;

        // dp(i, j) represents the length of the square
        // whose lower-right corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
        //dp[i][j] 表示 从这个格子开始 往上往左延伸, 最大 能够成`边长`多大的 正方形.
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                //当前点 是否 是 `1`.
                if (arrays[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

}
