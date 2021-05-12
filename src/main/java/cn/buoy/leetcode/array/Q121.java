package cn.buoy.leetcode.array;

public class Q121 {
    /**
     * https://www.youtube.com/watch?v=JD8QaYVq5lQ
     * 时刻update 最低点 用j标记,
     * i 继续往前遍历, 有更高收益, 则刷新最大收益,
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 0;
        int sofarMin = prices[0];
        for (int i = 0; i < prices.length; ++i) {
            //小于当前最小, 则与max比较, 更高则 update max
            if (prices[i] > sofarMin) {
                max = Math.max(max, prices[i] - sofarMin);
            } else {
                //找到更低点
                sofarMin = prices[i];
            }
        }
        return max;
    }
}
