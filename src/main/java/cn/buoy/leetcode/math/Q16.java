package cn.buoy.leetcode.math;

import java.util.Arrays;

public class Q16 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=Yg6q9P3Ln60
     * 思路: 3指針,
     * sum 直接相等 target 就返回; sum 小則 low++; sum 大則 high--,
     * 再優化去重 value.
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            //去掉 相同 value 但不同 index 的組合
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1;
            int high = nums.length - 1;

            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                // 有更小的 diff 的 sum, 則返回.
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    result = sum;
                }
                // sum 直接相等 target 就返回; sum 小則 low++; sum 大則 high--;
                if (target == sum) {
                    return sum;
                } else if (sum > target) {
                    high--;
                } else if (sum < target) {
                    low++;
                }
            }
        }
        return result;
    }

    // 淘汰
    public int threeSumClosest2(int[] nums, int target) {
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
