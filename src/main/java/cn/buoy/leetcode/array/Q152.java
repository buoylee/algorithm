package cn.buoy.leetcode.array;

public class Q152 {
    /**
     * https://www.youtube.com/watch?v=AtzfZHb35YI
     * 因为是乘法, 出现`负数`, 会使 最大最小互换.
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            //负数, 互换最大最小
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            //每次都比较 nums[i](抛弃掉i前边), 与 nums[i]和之前的累乘, 选最大的留下
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            //update 答案max
            max = Math.max(max, imax);
        }
        return max;
    }
}
