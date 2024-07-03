package cn.buoy.leetcode.math;

import java.util.Arrays;

public class Q259 {
    /**
     * 超簡單, 視頻, 註釋
     * https://www.youtube.com/watch?v=0G1lqGBGHzU
     * 思路:
     */
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        // 全靠排序
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int left = i + 1, right = len - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    // 細節: 已知 + nums[right] 已經比 target 小, 則高位從 left+1 到 right 都是比 target小, 數組已排序
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
