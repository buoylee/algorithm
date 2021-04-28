package cn.buoy.leetcode.dp;

public class Q375 {
    /**
     * https://www.youtube.com/watch?v=YTGHiGH_oTg
     * 题意: 在保证猜到对的情况下, 可以选的付的钱最少钱的值.
     */
    public int getMoneyAmount(int n) {
        //dp[i][j] 是指 从数字i~j范围内 最小的保证猜对的钱数.
        int[][] dp = new int[n + 1][n + 1];
        return minCost2(dp, 1, n);
    }

    /**
     * 这个理解简单点
     *
     * @param mem
     * @param lo
     * @param hi
     * @return
     */
    public int minCost2(int[][] mem, int lo, int hi) {
        if (lo >= hi) return 0;

        if (mem[lo][hi] != 0) return mem[lo][hi];

        int min = Integer.MAX_VALUE;

        //重点思路, 遍历每一种猜测. 在一直错到剩下一个答案的情况下, 选花费最小的.
        for (int i = lo; i <= hi; i++) {
            //分成前后2部分(lo到i - 1, i + 1到hi)后, 取 大的那边的值, 因为如果被猜数 在右边, 则也需要满足 足够的钱.
            int maxCost = i + Math.max(minCost2(mem, lo, i - 1), minCost2(mem, i + 1, hi));
            min = Math.min(min, maxCost);
        }

        mem[lo][hi] = min;

        return min;
    }

    /**
     * 解释: https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84775/3ms-Java-DP-solution
     *
     * @param dp
     * @param l
     * @param h
     * @return
     */
    private int minCost(int[][] dp, int l, int h) {
        //low 大于 high, 退出.
        if (l >= h) return 0;
        if (l + 1 == h) return l;
        if (dp[l][h] != 0) return dp[l][h];

        int minCost = Integer.MAX_VALUE;
        //大于一半的时候, 情况重复, 只是 左右交换了 而已.
        //这部优化, 可以用
        int mid = l + (h - l) / 2;
        //这个i -= 2 不知道怎么解释...
        for (int i = h - 1; i >= mid; i -= 2) {
            int maxCost = i + Math.max(minCost(dp, l, i - 1), minCost(dp, i + 1, h));
            minCost = Math.min(minCost, maxCost);
        }

        dp[l][h] = minCost;
        return minCost;
    }
}
