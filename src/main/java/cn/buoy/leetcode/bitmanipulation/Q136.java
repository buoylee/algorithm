package cn.buoy.leetcode.bitmanipulation;

public class Q136 {
    /**
     * 异或 easy题
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }
}
