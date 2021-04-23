package cn.buoy.leetcode.math;

import java.util.Arrays;

public class Q167 {
    /**
     * 这道题用 Q1 方法, 明明可以, 是用例问题吗? todo
     * <p>
     * https://www.youtube.com/watch?v=eX2CHl6iaxQ
     * 之前做的类似, 左右双向指针, 小了 移动左指针, 大了移动有指针.
     * 适用此方法是因为array已经排序.
     */
    public int[] twoSum(int[] num, int target) {
        int[] indice = new int[2];
        if (num == null || num.length < 2) return indice;
        int left = 0, right = num.length - 1;
        while (left < right) {
            int v = num[left] + num[right];
            if (v == target) {
                indice[0] = left + 1;
                indice[1] = right + 1;
                break;
            } else if (v > target) {
                right--;
            } else {
                left++;
            }
        }
        return indice;
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
