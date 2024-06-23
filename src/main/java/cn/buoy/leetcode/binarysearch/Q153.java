package cn.buoy.leetcode.binarysearch;

public class Q153 {
    /**
     * https://www.youtube.com/watch?v=gndg09dZSuI
     * 直接看視頻, 看圖, 然後看代碼就懂了, 簡單
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums[0] < nums[nums.length - 1]) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;

            }
        }
        return Math.min(nums[left], nums[right]);
    }
}
