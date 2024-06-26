package cn.buoy.leetcode.dp;

import java.util.Arrays;

public class Q322 {
    /**
     * https://www.youtube.com/watch?v=KzkQMXpWSuA
     * https://www.youtube.com/watch?v=aQhNCYN5TsU 優先, 這個短.
     * 相对简单, 和 279 很像.
     * 思路: dp[amount] 代表的是, 累積到 amount, 最小需要幾個 coin.
     * 遍歷dp[i]之前的所有dp, 找到 差一個值(coins 某個元素)的 dp, 這些 dp 的最小值 + 1 就是 dp[i].
     */
    public int coinChange(int[] coins, int amount) {
        //dp[i] 表示, 筹够i, 最少用 dp[i] 个硬币.
        int[] dp = new int[amount + 1];
        //dp 初始化 填充 可能的最大值 为 钱数本身, 因为 最多的情况是 每个coin都是`最小整数1`.
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        //这里j表示 最后一步 可以 筹成 i 块钱 的 j 种可能.
        for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
            //求 0~amount 可以最少几个coin 筹成 i块钱.
            for (int tempTarget = 0; tempTarget <= amount; tempTarget++) {
                //只有 钱数 大于等于 coins[coinIndex] 时, 这个基本条件, 才需要检查能不能 1個coin 筹到 tempTarget.
                // tempTarget - coins[coinIndex] 表示, 还差 coins[coinIndex] 就能湊到 tempTarget 錢數.
                if (tempTarget - coins[coinIndex] >= 0)
                    dp[tempTarget] = Math.min(dp[tempTarget], dp[tempTarget - coins[coinIndex]] + 1);
            }
        }
        // 如果湊不到 amount(不會超過 amount, 上邊判斷限制了 'i - coins[coinIndex] >= 0'), 返回-1
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
