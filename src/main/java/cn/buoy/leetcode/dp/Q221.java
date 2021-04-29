package cn.buoy.leetcode.dp;

public class Q221 {
    /**
     * https://www.youtube.com/watch?v=5AfIa6z3Yw0
     */
    public int maximalSquare(char[][] a) {
        if (a == null || a.length == 0 || a[0].length == 0)
            return 0;

        int max = 0, n = a.length, m = a[0].length;

        // dp(i, j) represents the length of the square
        // whose lower-right corner is located at (i, j)
        // dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
        //dp[i][j] 表示 从这个格子开始 往上往左延伸, 最大 能够成`边长`多大的 正方形.
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //当前点 是否 是 `1`.
                if (a[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        // return the area
        return max * max;
    }

}
