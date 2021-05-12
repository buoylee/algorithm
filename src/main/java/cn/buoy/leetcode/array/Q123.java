package cn.buoy.leetcode.array;

public class Q123 {
    /**
     * https://www.youtube.com/watch?v=a8xKiVTpdks&t=405s
     * 能理解 121题, 这题不难.
     * 视频有p1 那里讲错了点, 注意下.
     * 分别是从头, 尾开始遍历, 范围内 能得到的最大收益.
     * p1=[0, 0, 2, 2, 2, 3, 3, 4]。
     * p2=[4, 4, 4, 4, 4, 3, 3, 0]
     */
    public int maxProfit(int[] prices) {
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
