package cn.buoy.leetcode.dp;

public class Q375 {
    /**
     * 簡單, 看了視頻就懂
     * https://www.youtube.com/watch?v=YTGHiGH_oTg
     * 思路: 注意, 我们是要找到 至少能完成的 最小值.
     * 类似2分法, 先猜 任一點, 然後 按照 target 選擇一邊猜, 直到找到target. 所以, 可以想到 i + Math.max(minCost2(dp, low, i - 1), minCost2(dp, i + 1, high)), 然後一直用dfs就好.
     */
    public int getMoneyAmount(int n) {
        // dp[i][j] 是指 从数字i~j范围内 最小的保证猜对的钱数. 方便計算使用 n+1.
        int[][] dp = new int[n + 1][n + 1];
        return minCost2(dp, 1, n); // 题目要求 猜 1~n
    }

    /**
     * 找到 low 和 high 這個範圍內, 猜對最小需要多少錢.
     *
     * @param dp
     * @param low
     * @param high
     * @return
     */
    public int minCost2(int[][] dp, int low, int high) {
        if (low >= high) return 0;
        if (dp[low][high] != 0)
            return dp[low][high];
        int min = Integer.MAX_VALUE;
        // 重点: 遍历每一种猜测. 在一直错到剩下一个答案的情况下, 选花费最小的.
        for (int i = low; i <= high; i++) {
            // 關鍵: 理解Math.max(), 分成前后2部分(lo到i - 1, i + 1到hi)后, 取 大的那边的值, 因为被猜数 可能出現在 任一邊, 所以需要满足 大的一邊的要求.
            int leastCost = i + Math.max(minCost2(dp, low, i - 1), minCost2(dp, i + 1, high));
            min = Math.min(min, leastCost);
        }
        dp[low][high] = min;
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
