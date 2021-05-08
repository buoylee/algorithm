package cn.buoy.leetcode.design;

public class Q303 {
    /**
     *https://www.youtube.com/watch?v=pujaCH4UjYw
     * 构建 `nums[i] 等于 0~i 的sum` 的arr, 然后 如果求[i, j] 的sum, 用 sums[j] - sums[i-1]即可.
     */


}

class NumArray {

    int[] nums;

    public NumArray(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];

        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        if (i == 0)
            return nums[j];

        return nums[j] - nums[i - 1];
    }
}
