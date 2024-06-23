package cn.buoy.leetcode.bitmanipulation;

public class Q136 {
    /**
     * https://www.youtube.com/watch?v=F6YGxH0rfbg
     * 一句話, 思路: x^x = 0.
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }
}
