package cn.buoy.leetcode.array;

public class Q53 {
    /**
     * https://www.youtube.com/watch?v=EcJ0wBBMoKA
     */
    public int maxSubArray(int[] A) {
        int n = A.length;
        //到当前元素为止, 最大的subnum sum 值,
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        //max是 dp[i]的最大值.
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            //最大值是 当前值 + 以往最大值(大于0的时候)
            dp[i] = A[i] + (Math.max(dp[i - 1], 0));
            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
