package cn.buoy.leetcode.math;

import java.util.Arrays;

public class Q167 {
    /**
     * 簡單, 視頻.
     * https://www.youtube.com/watch?v=eX2CHl6iaxQ
     * 思路: 因为array已经排序, 可以使用左右双向指针, 小了移动左指针, 大了移动右指针.
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return new int[]{-1, -1};
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                // 返回index是從1開始數的.
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 用 target 和 每个元素 的差, 用 2分法 去找 i + 1 到 n - 1 与差相同的数.
     * 慢, 不用.
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = Arrays.binarySearch(nums, i + 1, n, target - nums[i]);
            if (j >= 0) {
                return new int[]{i + 1, j + 1};
            }
        }
        return new int[]{-1, -1};
    }
}
