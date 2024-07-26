package cn.buoy.leetcode.array;

public class Q152 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=AtzfZHb35YI
     * 思路: dpMax[i]: 到 i 为止(不一定是 index = 0 为 arr 的起始) 可能的最大積, dpMin[i]: 到 i 为止, 可能的最大積.
     * 为什么需要 dpMin, 因为, 可能会出现 负数. 就可能反而有更大的乘积.
     * dpMax 和 dpMin 都是, "当前 num" 和 相应 dp[i - 1] * "当前 num" 中, 选较大/小的.
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, dpMax = 1, dpMin = 1;
        for (int i = 0; i < nums.length; i++) {
            // 关键: 负数, 为了下边 max() 代码统一, 直接互换
            if (nums[i] < 0) {
                int tmp = dpMax;
                dpMax = dpMin;
                dpMin = tmp;
            }
            // 关键: 每次都选 nums[i](抛弃掉i前边), 与 nums[i] 和 dp[i - 1] 累乘, 中 较大的.
            dpMax = Math.max(dpMax * nums[i], nums[i]);
            dpMin = Math.min(dpMin * nums[i], nums[i]);
            max = Math.max(max, dpMax);
        }
        return max;
    }
}
