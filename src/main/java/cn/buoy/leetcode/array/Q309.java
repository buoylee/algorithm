package cn.buoy.leetcode.array;

public class Q309 {
    /**
     * 相對簡單, 視頻
     * https://www.youtube.com/watch?v=5mcq_V2-JlU
     * 思路: 定義2種狀態, 當天持有, 當天不持有:
     * 當天持有 = (上一天 hold, 不动;) 或 (上上一天 nohold, cooldown, 现在變 hold) 2者中, 較大的那個
     * 當天不持有 = (上一天 nohold, 不动;) 或 (上一天 hold, 现在變 nohold;) 2者中, 較大的那個
     * dp 到結束.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int len = prices.length;
        // 表示 當天 持有/不持有 這種狀態時的, 最大收益.
        int[] hold = new int[len];
        int[] nohold = new int[len];
        hold[0] = -prices[0];
        // 看下邊 hold/nohold 解釋.
        hold[1] = Math.max(hold[0], -prices[1]);
        nohold[1] = Math.max(nohold[0], hold[0] + prices[1]);
        for (int i = 2; i < len; i++) {
            //關鍵:
            // 當天 hold stock 包括:
            hold[i] = Math.max(hold[i - 1], // 上一天 hold, 不动;
                    nohold[i - 2] - prices[i]); // 上上一天 nohold, 上一天 cooldown, 现在變 hold;
            // 當天 nohold stock 包括:
            nohold[i] = Math.max(nohold[i - 1], // 上一天 nohold, 不动;
                    hold[i - 1] + prices[i]); // 上一天 hold, 现在變 nohold;
        }
        return nohold[len - 1];
    }

    // 另一種狀態分類
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        int[] hold = new int[n]; // 持有股票
        int[] notHoldCooldown = new int[n]; // 不持有股票且在冷冻期
        int[] notHoldNoCooldown = new int[n]; // 不持有股票且不在冷冻期

        hold[0] = -prices[0];
        notHoldCooldown[0] = 0;
        notHoldNoCooldown[0] = 0;

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], notHoldNoCooldown[i - 1] - prices[i]);
            notHoldCooldown[i] = hold[i - 1] + prices[i];
            notHoldNoCooldown[i] = Math.max(notHoldNoCooldown[i - 1], notHoldCooldown[i - 1]);
        }

        return Math.max(notHoldCooldown[n - 1], notHoldNoCooldown[n - 1]);
    }
}
