package cn.buoy.leetcode.dp;

public class Q70 {
    /**
     * 超简单.
     * https://www.youtube.com/watch?v=k3e-x8Uhwpw
     * 思路: 到 "第n" step, 只有2类情况, dp[n-1](前 1步), dp[n-2](前 2步)到达 n step. dp[n] 代表 到第n step 有几种方式.
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        if (n == 1) return 1;
        if (n == 2) return 2;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }
}
