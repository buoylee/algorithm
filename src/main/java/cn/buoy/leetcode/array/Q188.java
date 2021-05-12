package cn.buoy.leetcode.array;

public class Q188 {
    /**
     * https://www.youtube.com/watch?v=ZMgTmDvAp6g
     * 第一种思考方式可以参考 之前的 题目, 但是会超时.
     * <p>
     * 一次trx 等于 1次买 和 卖
     * 上一次的卖可以和下一次的买 同一天; 但是本次的 买卖 不能是 同一天.
     * <p>
     * <p>
     * https://www.bilibili.com/video/BV15A411J7E6?from=search&seid=9202000296362953880
     * 另一种思路, 以后再看.
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        //一次trx 等于 1次买 和 卖, 所以 可以足够 每次 在低位买, 高位卖.
        if (k >= len / 2) return quickSolve(prices);

//        if(len ==0) return 0;

        //dp[i][j]表示 在 第i次交易在第j天时, 最大的收益
        //多给一行, 都是0, 可以用相同的转义方程来表示. 因为dp[i][j] 与 i-1, j-1相关.
        int[][] dp = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            //表示 在 过往 i-1次 交易后 + j天买进(不卖除) 的 最大收益,
            int tmpMax = -prices[0];
            for (int j = 1; j < len; j++) {
                // 有2种情况, 今天什么都没做, 就等于j次交易`昨天为止`的最大值; 今天卖掉(之前有买才能卖, 卖掉肯定是收益增加了).
                //现在, 剩下就是求 之前某一天上次交易(i-1)完,且当天买进的price值 的和 的最大值.
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmpMax);
                //先看懂这行
                //某前一天最大收益 + 当天j 买入 扣除的价钱, 和以往比找出最大值.
                //dp[i - 1][j - 1]也通过... 奇怪
                //这里要如何理解都要当天j的dp - 同天的price,
                //其实应该要理解成, 假如要在j天买入, 这里是要加上的是 `到上次交易为止` j天的最大收益!
                //不要想反了
                tmpMax = Math.max(tmpMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
