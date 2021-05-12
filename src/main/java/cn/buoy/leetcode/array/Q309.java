package cn.buoy.leetcode.array;

public class Q309 {
    /**
     * https://www.youtube.com/watch?v=5mcq_V2-JlU
     * 注意分析状态, 还是可以想出来的.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;

        int[] hold = new int[n];
        int[] nohold = new int[n];
        hold[0] = -prices[0];
        hold[1] = Math.max(hold[0], -prices[1]);
        nohold[1] = Math.max(nohold[0], hold[0] + prices[1]);
        for (int i = 2; i < n; i++) {
            //持有 包括:
            // 上一步hold, 现在不动;
            // 上上一步刚nohold, 隔一天, 现在变为hold
            hold[i] = Math.max(hold[i - 1], nohold[i - 2] - prices[i]);
            //不持有 包括:
            // 上一步nohold, 现在不动;
            // 上一步hold, 现在卖出
            nohold[i] = Math.max(nohold[i - 1], hold[i - 1] + prices[i]);
        }
        return nohold[n - 1];
    }
}
