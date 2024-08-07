package cn.buoy.leetcode.array;

public class Q122 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=MujR32D7LRo
     * 思路: 可以無限次交易, 那就只買漲的就好, 即 只要 prices[i] > prices[i - 1], 就 += result
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++)
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        return maxprofit;
    }

    /**
     *
     * https://www.youtube.com/watch?v=USEFjOtuyA4
     * dp
     */
    // todo

    /**
     * 跳過.
     * 先找到下一个最小的index(这个index的下一个index将上升), 在根据刚刚找到的最小的index找下一个最大的(这个最大的index, 表示这个index的下一个index下降)
     */
    public int maxProfit2(int[] prices) {
        int profit = 0, i = 0;
        while (i < prices.length) {
            // find next local minimum
            while (i < prices.length - 1 && prices[i + 1] <= prices[i]) i++;
            int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
            // find next local maximum
            while (i < prices.length - 1 && prices[i + 1] >= prices[i]) i++;
            profit += i < prices.length ? prices[i++] - min : 0;
        }
        return profit;
    }
}
