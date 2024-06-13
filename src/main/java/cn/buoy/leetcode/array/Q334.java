package cn.buoy.leetcode.array;

import java.util.List;

public class Q334 {
    /**
     * https://www.youtube.com/watch?v=jaPWrswvAd0
     * 原理不解, 类似题目Q300
     * <p>
     * 例子: [3, 1, 5, 2, 3...]: min3; min1; min3 mid5; min2 mid5; min2 mid3;...
     */
    public boolean increasingTriplet(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            //细节在这: 每次更新, 都会先和min比, 关键在于(如果有一个更小的出现, 会直接替换掉min, mid只有在min存在的情况下才会出现, 并不是单纯的找第1小和第2小的排序. 且后续替换的值只会比之前出现过的更小), 使得刚好满足递增的要求,
            if (n <= small) {
                small = n;
            } // update small if n is smaller than both
            else if (n <= big) {
                big = n;
            } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
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
