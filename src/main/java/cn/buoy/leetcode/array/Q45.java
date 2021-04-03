package cn.buoy.leetcode.array;

public class Q45 {
    /*
    https://www.youtube.com/watch?v=MknETKNOz_s
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0, curMax = 0, newMax = 0;
        for (int i = 0; i < len - 1; i++) {
            newMax = Math.max(newMax, i + nums[i]);
            if (i == curMax) {
                step++;
                curMax = newMax;
            }
        }
        return step;
    }
}
