package cn.buoy.leetcode.array;

public class Q45 {
    /*
    https://www.youtube.com/watch?v=MknETKNOz_s
     */
    //fixme 贪婪, 选步数范围内最大值, 当到达最范围内最远处累加步数.
    public int jump(int[] nums) {
        int len = nums.length;
        int step = 0, farestReachingIndex = 0, currentIndexFarestReachingIndex = 0;
        for (int i = 0; i < len - 1; i++) {
            currentIndexFarestReachingIndex = Math.max(currentIndexFarestReachingIndex, i + nums[i]);
            if (i == farestReachingIndex) {
                step++;
                farestReachingIndex = currentIndexFarestReachingIndex;
            }
        }
        return step;
    }
}
