package cn.buoy.leetcode.binarysearch;

public class Q154 {
    /**
     * https://www.youtube.com/watch?v=w_OIDPaf_eI
     * 和前边的33, 81 变化一样样(這些是找某target). nums[mid] == nums[left] 的時候 ++ 即可.
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums[0] < nums[nums.length - 1]) return nums[0];
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 求最小, 只需要和右比
            if (nums[mid] > nums[right])
                left = mid;
            else if (nums[mid] == nums[left])
                //差别只在对 [mid]相等时 的处理.
                left++;
            else
                right = mid;
        }
        return Math.min(nums[left], nums[right]);
    }
}
