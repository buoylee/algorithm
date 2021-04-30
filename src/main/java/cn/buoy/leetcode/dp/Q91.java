package cn.buoy.leetcode.dp;

public class Q91 {
    /**
     * https://www.youtube.com/watch?v=Q26NujacIpM
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        //dp[i] 表示, 0~i位个数, 可以有多少种组合.
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            //每个dp[i] 有2类组合方式, 1位(string[i]) * dp[i-1] 或 2位(string[i-1~i]) * dp[i-2]
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (first != 0) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}
