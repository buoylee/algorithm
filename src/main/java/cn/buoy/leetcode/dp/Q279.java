package cn.buoy.leetcode.dp;

import java.util.Arrays;

public class Q279 {
    /**
     * https://www.youtube.com/watch?v=TXGvkQLVSgA
     * 还是相对简单, 有思路的话. 322.
     * 思路: 從 0-n dp, 注意細節看註釋.
     */
    public int numSquares(int n) {
        //dp[sum], 表示的是, 加總sum 需要最少的 完全平方数 个数.
        // 要包括 0, 所以需要 n + 1 個位置.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        // 完全沒有必要, 可以省略, 通過下邊遍歷就可以得到例如: dp[9] == 1.
        // 在i*i的 index上 都是 有最小值 为 1的(只用1个 完全平方数 即可).
//        for (int i = 1; i * i <= n; i++) {
//            dp[i * i] = 1;
//        }

        // 從 dp 1-n 小到大 開始找.
        for (int sum = 1; sum <= n; sum++) {
            //關鍵: j*j 組成 '完全平方數', 因爲 '每個加數' 都要保證是 '完全平方數', 所以 加數 最大應該在 j^2 的範圍內.
            for (int i = 1; i * i <= sum; i++) {
                // 關鍵: 減去的數也保證了是'完全平方數'.
                dp[sum] = Math.min(dp[sum], 1 + dp[sum - i * i]);
            }
        }
        return dp[n];
    }

}
