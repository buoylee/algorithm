package cn.buoy.leetcode.binarysearch;

public class Q34 {

    public static void main(String[] args) {
        Q34 q34 = new Q34();
        q34.searchRange(new int[]{}, 0);
    }

    /**
     * https://www.youtube.com/watch?v=NlLP8Op_4zw
     * 看視頻, 簡單
     * 思路: 各找左右target邊界
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int len = nums.length;
        int lo = 0, hi = len - 1;
        // 左邊界
        // 注意!
        while (lo + 1 < hi) {
            // 注意!
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target)
                lo = mid;
            else
                hi = mid;
        }
        //因为2個都有可能, 但是先找左邊的, 如果是target, 那右旁邊哪個一定也是, 所以找左边第一个的話, 优先从左边找.
        int first = nums[lo] == target ? lo : nums[hi] == target ? hi : -1;

        // 右邊界
        lo = 0;
        hi = len - 1;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target)
                hi = mid;
            else
                lo = mid;

        }
        //同理, 右邊從右边先找.
        int end = nums[hi] == target ? hi : nums[lo] == target ? lo : -1;

        return new int[]{first, end};
    }
}
