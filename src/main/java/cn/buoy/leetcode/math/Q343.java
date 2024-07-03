package cn.buoy.leetcode.math;

public class Q343 {
    /**
     * https://www.youtube.com/watch?v=C6NnVbObodY
     * 思路: DP, 從 2開始 切2分, 直到 n 切2分.
     */
    public int integerBreak(int n) {
        //dp[i] means output when input = i, e.g. dp[4] = 4 (2*2),dp[8] = 18 (2*2*3)...
        int[] dp = new int[n + 1];
        dp[1] = 1;
        // fill the entire dp array
        // 外層限制範圍, 內層切分
        for (int i = 2; i <= n; i++) {
            //let's say i = 8, we are trying to fill dp[8]:if 8 can only be broken into 2 parts, the answer could be among 1 * 7, 2 * 6, 3 * 5, 4 * 4... but these numbers can be further broken. so we have to compare 1 with dp[1], 7 with dp[7], 2 with dp[2], 6 with dp[6]...etc
            // 8 拆分成 1, 7 和 7, 1 是一樣的
            for (int j = 1; j <= i / 2; j++) {
                // use Math.max(dp[i],....)  so dp[i] maintain the greatest value
                //对每种拆分方式找最大值, 还有就是, 对逻辑拆分成的2半, 还会有2种情况, 就是2半 不再各自拆分的情况. 如下 j, i-j.
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }
}
