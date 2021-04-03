package cn.buoy.leetcode.array;

public class Q121 {
    /*
    https://www.youtube.com/watch?v=JD8QaYVq5lQ

    只要找到最低点用j标记,
    在最低点, 右边找更高, 有则刷新最大收益,
    出现最小值, 用j标记, 在找右边最高点.
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 0;
        int sofarMin = prices[0];
        for (int i = 0; i < prices.length; ++i) {
            if (prices[i] > sofarMin) {
                max = Math.max(max, prices[i] - sofarMin);
            } else {
                sofarMin = prices[i];
            }
        }
        return max;
    }
}
