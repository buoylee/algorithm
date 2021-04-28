package cn.buoy.leetcode.dp;

public class Q62 {
    /**
     * https://www.youtube.com/watch?v=L6dWXuh8BuE
     * 可以简化为1维dp, 不过稍微不好理解点.
     */
    //有m行, n列
    public int uniquePaths(int m, int n) {
        Integer[][] dp = new Integer[m][n];
        //第一行, 第一列都是 只有1种走法, 一路到底.
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        //关键: 走到 dp[x][y] 的方式只有2种, dp[x-1][y] 或者 dp[x][y-1],
        //所以 只要统计 (0,0) 分别到(x-1,y), (x,y-1) 的 路径数 的和 就是 (0,0)到(x,y) 的路径树.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
