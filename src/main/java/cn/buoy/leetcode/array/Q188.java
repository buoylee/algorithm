package cn.buoy.leetcode.array;

public class Q188 {
    /**
     * 看註釋, 能看懂, 需要複習.
     * https://www.youtube.com/watch?v=ZMgTmDvAp6g 從 24:00 開始看.
     * 第一种思考方式可以参考 之前的 题目, 但是会超时.
     * <p>
     * 一次trx 等于 1次买 和 卖
     * 上一次的卖可以和下一次的买 同一天; 但是本次的 买卖 不能是 同一天.
     * <p>
     * https://www.bilibili.com/video/BV15A411J7E6?from=search&seid=9202000296362953880
     * 另一种思路, 以后再看.
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
//        if (len == 0) return 0;
        // 一次trx 等于1次 "买和卖", 所以足够每次在低位买, 高位卖.
        if (k >= len / 2) return quickSolve(prices);
        //dp[currTrx][currDay]表示 在 第 currTrx 次交易在第 currDay 天时, 最大的收益.
        //多给一行, 都是0, 可以用相同的转义方程来表示. 因为dp[i][j] 需要用到 i-1, j-1.
        int[][] dp = new int[k + 1][len];
        for (int currTrx = 1; currTrx <= k; currTrx++) {
            //表示 在 过往 "currTrx - 1 次" 交易 + currDay 天买进(不卖出) 的最大收益,
            int tmpMax = -prices[0];
            for (int currDay = 1; currDay < len; currDay++) {
                // dp 有2种情况:
                // 1. 今天什么都不做, 即, 做完 currTrx 次交易(已經賣掉, 才是最高收益) && `昨天为止` 的最大值;
                // 2. tmpMax 表示, 之前某一天上次交易 urrTrx-1(currTrx 現在還沒賣出, 所以只能算 currTrx-1), 加上 "当天賣出的price" 的最大值.
                dp[currTrx][currDay] = Math.max(dp[currTrx][currDay - 1], prices[currDay] + tmpMax);
                //先看懂这行
                //某前一天最大收益 + 当天j 买入 扣除的价钱, 和以往比找出最大值.
                // tmpMax 表示, 在 currDay 的 `到上次交易为止` 且在 currDay 天买入, 但不賣出 的最大收益, 這個並不是 dp, 而是爲了計算 dp, 所需的 "未賣出時的最大收益"
                tmpMax = Math.max(tmpMax, dp[currTrx - 1][currDay] - prices[currDay]);
            }
        }
        return dp[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        return profit;
    }
}
