package cn.buoy.leetcode.math;

public class Q396 {
    /**
     * 找到规律就好做, 视频(视频旋轉方向翻了).
     * https://www.youtube.com/watch?v=NNXlLYPJL_s
     * DP[i] = DP[i-1] + (sum - arr.length * arr[i-1])
     */
    public int maxRotateFunction(int[] arr) {
        int len = arr.length;
        if (len == 0 || len == 1) return 0;
        int sum = 0, dp0 = 0, max = Integer.MIN_VALUE;
        int[] dp = new int[len + 1];
        // 求dp[0]
        for (int i = 0; i < len; i++) {
            // 公式需要
            sum += arr[i];
            dp0 += i * arr[i];
        }
        dp[0] = dp0;
        for (int i = 1; i <= len; i++) {
            // 关键公式
            dp[i] = dp[i - 1] + sum - len * arr[len - i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

}
