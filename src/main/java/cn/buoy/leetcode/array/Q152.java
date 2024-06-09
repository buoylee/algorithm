package cn.buoy.leetcode.array;

public class Q152 {
    /**
     * https://www.youtube.com/watch?v=AtzfZHb35YI
     * dpMax[i]: 從(0到i之間)到i可能的最大積
     * dpMin[i]: 從(0到i之間)到i可能的最小積
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, dpMax = 1, dpMin = 1;
        for (int i = 0; i < nums.length; i++) {
            //负数, 互换最大最小
            if (nums[i] < 0) {
                int tmp = dpMax;
                dpMax = dpMin;
                dpMin = tmp;
            }
            //每次都比较 nums[i](抛弃掉i前边), 与 nums[i]和之前的累乘, 选最大的留下
            dpMax = Math.max(dpMax * nums[i], nums[i]);
            dpMin = Math.min(dpMin * nums[i], nums[i]);
            //update 答案max
            max = Math.max(max, dpMax);
        }
        return max;
    }
}
