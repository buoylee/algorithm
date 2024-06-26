package cn.buoy.leetcode.dp;

public class Q198 {
    /**
     * https://www.youtube.com/watch?v=-i2BFAU25Zk&t=2s
     * 簡單
     * 思路: 分2個 dp, 本 house 偷與不偷. 然後根據前一個房子偷與不偷的情況:
     * 1. dp偷[i] = dp不偷[i-1] + house[i]
     * 2. dp不偷[i] = max(dp偷[i-1], dp偷[i-1])
     * 思路是dp, 實際不需要2個dp[]來記錄, 只需要2個變量, 因爲只需要考慮 前一位dp.
     */
    public int rob(int[] nums) {
        int rob = 0;
        int norob = 0;
        for (int i = 0; i < nums.length; i++) {
            int newRob = norob + nums[i];
            int newNoRob = Math.max(norob, rob);
            rob = newRob;
            norob = newNoRob;
        }
        return Math.max(rob, norob);
    }
}
