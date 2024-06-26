package cn.buoy.leetcode.dp;

public class Q213 {
    /**
     * https://www.youtube.com/watch?v=-i2BFAU25Zk&t=2s
     * (舊)337题做过, 跳过
     * <p>
     * 優化厚的代碼, 可以複習下.
     * 思路: 同198, 分成偷與不偷2個dp, 再加上, 第一個偷/不偷的緯度. 排除掉不合理的一個解(第一個偷, 最後一個也偷), 剩下都是合理的, 選出最大值.
     * 下邊代碼是優化過的,
     * 1. startFromFirstHouse[i - 2] 偷, startFromFirstHouse[i - 1] 不偷, startFromFirstHouse[i]偷; 所以 這種情況 肯定是 startFromFirstHouse[i - 2] + num[i].
     * 2. startFromFirstHouse[i - 2] 不偷, startFromFirstHouse[i - 1] 偷, startFromFirstHouse[i]不偷; 所以 這種情況 肯定是 startFromFirstHouse[i - 1].
     * 簡化結果就是 max(startFromFirstHouse[i - 2] + num[i], startFromFirstHouse[i - 1])
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // Case 1: Do not rob the first house
        // 關鍵: 不偷第1個, 最後一個可以偷
        int maxMoney1 = robLinear(nums, 1, nums.length - 1);
        // Case 2: Do not rob the last house
        // 關鍵: 偷第1個, 最後一個不能偷
        int maxMoney2 = robLinear(nums, 0, nums.length - 2);

        return Math.max(maxMoney1, maxMoney2);
    }

    private int robLinear(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int n = end - start + 1;
        int[] dp = new int[n];

        // 關鍵: dp[0] 就是 start位置
        dp[0] = nums[start];
        // 2個數選一個偷, 只能 2選1.
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i]);
        }

        return dp[n - 1];
    }
}
