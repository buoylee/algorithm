package cn.buoy.leetcode.binarysearch;

public class Q162 {
    /**
     * https://www.youtube.com/watch?v=etuTPmks7Dc
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int lo = 0, hi = n - 1;
        //这种写法, 可以避免死循环问题(), 这种方式最后结束时, lo, hi肯定相邻.
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            //找峰值点, 肯定存在一个关系 [lo] < [mid] > [hi]
            //保持 `/ \` 一直找, 总会找到一个peak.
            if (nums[mid] < nums[mid + 1]) {
                //这个mid 是个 斜率为正 向上
                lo = mid;
            } else {
                //这个mid 是个 斜率为负 向下
                hi = mid;
            }
        }
        return nums[lo] > nums[hi] ? lo : hi;
    }
}
