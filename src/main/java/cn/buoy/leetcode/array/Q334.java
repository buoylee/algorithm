package cn.buoy.leetcode.array;

import java.util.List;

public class Q334 {
    /**
     * 300
     * 直到這個算法就簡單, 不然就 dp
     * https://www.youtube.com/watch?v=jaPWrswvAd0
     * 思路: curr < min, 就放 min;(注意: 這裏 mid 不會改變, 如果下一個 val 是比 mid 大的, 直接找到結果)
     * min < curr < mid, 就放 mid;
     * mid < curr, 返回.
     * <p>
     * 例子: [3, 1, 5, 2, 3...]:
     * min3;
     * min1;
     * min3 mid5;
     * min2 mid5;
     * min2 mid3;
     * ...
     */
    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int num : nums) {
            // 细节: 每次更新, 都会先和min比, 关键在于(如果有一个更小的出现, 会直接替换掉min, mid只有在min存在的情况下才会出现, 并不是单纯的找第1小和第2小的排序. 且后续替换的值只会比之前出现过的更小), 使得刚好满足递增的要求,
            if (num <= min) // update min if num is smaller than both
                min = num;
            else if (num <= mid) // update mid only if greater than min but smaller than mid
                mid = num;
            else
                return true; // return if you find a number bigger than both
        }
        return false;
    }

    /*
    https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79053/My-way-to-approach-such-a-problem.-How-to-think-about-it-Explanation-of-my-think-flow.
     */
    //todo dp
    public boolean increasingTriplet1(int[] nums) {
        return false;
    }
}
