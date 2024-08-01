package cn.buoy.leetcode.bitmanipulation;

public class Q136 {
    /**
     * 简单, 视频
     * https://www.youtube.com/watch?v=F6YGxH0rfbg
     * 思路: "异或" 消除(变为0)成对出现的 num.
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums)
            result ^= i;
        return result;
    }
}
