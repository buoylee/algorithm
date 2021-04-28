package cn.buoy.leetcode.dp;

import java.util.Arrays;

public class Q322 {
    /**
     * https://www.youtube.com/watch?v=KzkQMXpWSuA
     * 相对简单
     */
    public int coinChange(int[] coins, int amount) {
        //dp[i] 表示, 筹够i, 最少用dp[i]个硬币.
        int[] dp = new int[amount + 1];
        //dp 初始化 填充 可能的最大值 为 钱数本身, 因为 最多的情况是 每个coin都是`最小整数1`.
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        //这里j表示 最后一步 可以 筹成 i 块钱 的 j 种可能.
        for (int j = 0; j < coins.length; j++) {
            //求 0~amount 可以最少几个coin 筹成 i块钱.
            for (int i = 0; i <= amount; i++) {
                //只有 钱数 大于等于 coins[j] 时, 这个基本条件, 才需要检查能不能筹到i. i - coins[j] 表示 之前筹到的  离最后i块 还差 coins[j]块 的 dp[x]的最小币数.
                //所以找到 dp[x] + 1 即是dp[i].
                if (i - coins[j] >= 0) dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
