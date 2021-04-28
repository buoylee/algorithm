package cn.buoy.leetcode.dp;

import java.util.Arrays;

public class Q279 {
    /**
     * https://www.bilibili.com/video/BV1ft411j7gF/?spm_id_from=333.788.recommend_more_video.-1
     * 还是相对简单, 有思路的话
     */
    public int numSquares(int n) {
        //这里的dp[i] 的i 指的是最后的和为i , dp[i]则是 和为i的 可能的 最小平方数 个数.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        //在i*i的 index上 都是 有最小值 为 1的(只用1个 完全平方数 即可).
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }

        //System.out.println(Arrays.toString(dp));
        //到i 的 最少平方数开始遍历
        for (int i = 1; i <= n; i++) {
            //为什么到 <= j*j 为止, 如果一个平方数都超过了i, 不符合.
            for (int j = 1; j * j <= i; j++) {
                //找出 合理的j*j 范围内的数 + dp[i - j * j] 的所有可能 的 最小值.
                dp[i] = Math.min(dp[i], 1 + dp[i - j * j]);
            }
        }
        return dp[n];
    }

}
