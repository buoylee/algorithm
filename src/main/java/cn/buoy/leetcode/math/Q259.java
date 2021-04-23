package cn.buoy.leetcode.math;

import java.util.Arrays;

public class Q259 {
    /**
     * https://www.youtube.com/watch?v=0G1lqGBGHzU
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len - 2; i++) {
            int left = i + 1, right = len - 1;
            while (left < right) {
                //只要小于了 剩下的都满足
                if (nums[i] + nums[left] + nums[right] < target) {
                    count += right - left;
                    //加完, 记得left++ 继续找
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }
}
