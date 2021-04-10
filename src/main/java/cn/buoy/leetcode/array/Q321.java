package cn.buoy.leetcode.array;

public class Q321 {

    /*
    分2步走:

    删除k个数, 不改变顺序, 使数字最小; leetcode 402
    https://leetcode-cn.com/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/


     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int[] ans = new int[k];
        for (int i = Math.max(0, k - m); i <= k && i <= n; ++i) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, ans, 0))
                ans = candidate;
        }
        return ans;
    }

    //合并2个数组
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; ++r)
            ans[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return ans;
    }

    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    public int[] maxArray(int[] nums, int subArrSize) {
        int n = nums.length;
        int[] ans = new int[subArrSize];
        for (int i = 0, stackSize = 0; i < n; ++i) {
            //剩余数字 + 栈元素个数 > 子数组长度...
            while (n - i + stackSize > subArrSize && stackSize > 0 && ans[stackSize - 1] < nums[i])
                stackSize--;
            if (stackSize < subArrSize)
                ans[stackSize++] = nums[i];
        }
        return ans;
    }
}
