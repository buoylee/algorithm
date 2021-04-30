package cn.buoy.leetcode.binarysearch;

public class Q35 {
    /**
     * https://www.youtube.com/watch?v=W15LJZqpnfM
     * 这题和 278 的边界处理 很像.
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;
        int left = 0, right = nums.length - 1;
        //模板写法
        while (left < right - 1) {  // while (left < right-1) is a useful technique
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid;
            else
                right = mid;
        }
        if (nums[left] > nums[right])
            return nums[right];
        return nums[left];
    }
}
