package cn.buoy.leetcode.binarysearch;

public class Q34 {

    public static void main(String[] args) {
        Q34 q34 = new Q34();
        q34.searchRange(new int[]{}, 0);
    }

    /**
     * https://www.youtube.com/watch?v=NlLP8Op_4zw
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int len = nums.length;
        int lo = 0, hi = len - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target)
                lo = mid;
            else
                hi = mid;
        }
        //因为如果2个相同的相邻, 找右边第一个需要优先从右边找.
        int first = nums[lo] == target ? lo : nums[hi] == target ? hi : -1;

        lo = 0;
        hi = len - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target)
                hi = mid;
            else
                lo = mid;

        }
        //从右边先找.
        int end = nums[hi] == target ? hi : nums[lo] == target ? lo : -1;

        return new int[]{first, end};
    }
}
