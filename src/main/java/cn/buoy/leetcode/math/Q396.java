package cn.buoy.leetcode.math;

public class Q396 {
    /**
     * https://www.youtube.com/watch?v=NNXlLYPJL_s
     * 找到规律就好做... 推导过程忘了看视频(视频写反了), leetcode.
     * DP[i] = DP[i-1] + (sum - arr.length * arr[i-1])
     */
    public int maxRotateFunction(int[] A) {
        int len = A.length;
        if (len == 0 || len == 1) return 0;
        int sum = 0, dp0 = 0, max = Integer.MIN_VALUE;
        int[] dp = new int[len + 1];
        //先求出初始状态, dp[0], sum 为 后续DP 必要初始条件.
        for (int i = 0; i < len; i++) {
            sum += A[i];
            dp0 += i * A[i];
        }
        dp[0] = dp0;
        for (int i = 1; i <= len; i++) {
            //关键
            dp[i] = dp[i - 1] + sum - len * A[len - i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

}
