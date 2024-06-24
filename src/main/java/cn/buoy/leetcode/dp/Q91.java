package cn.buoy.leetcode.dp;

public class Q91 {
    /**
     * https://www.youtube.com/watch?v=Q26NujacIpM
     * 思路: 不要想太多, 就是1或2步的問題.
     * dp[i] == dp[i-1](和前一個數字切分, 所以看前一個數字的數量) + dp[i-2](和前一個數字合併爲一個, 所以看前兩個數字的數量).
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
