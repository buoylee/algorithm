package cn.buoy.leetcode.array;

public class Q123 {
    /**
     * 简单, 懂思路的话.
     * https://www.youtube.com/watch?v=USEFjOtuyA4&t=995s 时间点: 10:13
     * 思路: 4种状态, 当前处于 first buy; 当前处于 first sell; 当前处于 second buy; 当前处于 second sell;
     * dp 方式求出 4种状态 在第 i 天的 max.
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        // init
        // 第一次买入
        int buy1 = -prices[0];
        int sell1 = 0;
        // 关键: 第一天完成第1次交易后, 继续第2次买入.
        int buy2 = -prices[0];
        // 第2次卖出
        int sell2 = 0;
        for (int i = 1; i < n; i++) {
            // 关键: 每天都可以选择 "什么都不做" 和 "对应操作". 为什么不需要增加 "什么都不做的状态", 因为结果和 "同一天买卖" 的结果一样.
            // 注意, 这里 buy1 不需要 Math.max(buy1, buy1 - prices[i]), 因为他妈就是 "第一次 buy".
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return Math.max(sell1, sell2);
    }

    /**
     * 这个方法不够通用
     * 思路: 找出前后2个区间内最大的收益的可能index
     * https://www.youtube.com/watch?v=a8xKiVTpdks&t=405s
     * 视频有p1 那里讲错了点, 注意下.
     * 分别是从头, 尾开始遍历, 范围内 能得到的最大收益.
     * p1=[0, 0, 2, 2, 2, 3, 3, 4]。
     * p2=[4, 4, 4, 4, 4, 3, 3, 0]
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int[] trx1 = new int[prices.length];
        int[] trx2 = new int[prices.length];

        int lowest = prices[0];
        for (int i = 1; i < prices.length; i++) {
            trx1[i] = Math.max(trx1[i - 1], prices[i] - lowest);
            lowest = Math.min(lowest, prices[i]);
        }
        int highest = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            trx2[i] = Math.max(trx2[i + 1], highest - prices[i]);
            highest = Math.max(highest, prices[i]);
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, trx1[i] + trx2[i]);
        }
        return res;
    }
}
