package cn.buoy.leetcode.math;

import java.util.Arrays;

public class Q16 {
    /**
     * https://www.youtube.com/watch?v=Yg6q9P3Ln60
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closet = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                //区别就只在这里
                if (Math.abs(sum - target) < Math.abs(closet - target))
                    closet = sum;

                if (sum > target) {
                    //这些都是去重
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                } else if (sum < target) {
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                } else
                    //相等直接返回
                    return sum;
            }
        }
        return closet;
    }
}
