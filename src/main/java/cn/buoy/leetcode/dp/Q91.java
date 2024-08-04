package cn.buoy.leetcode.dp;

public class Q91 {
    /**
     * 简单
     * https://www.youtube.com/watch?v=Q26NujacIpM
     * 思路: 转化为, 取1位或2位(26个字母)的问题(1或2步的问题).
     * dp[i] == dp[i-1](和 前一個數字 分开解释, 所以看前一個數字的 "dp 數量") + dp[i-2](当前数字 和 前一個數字, 2位数 解析为 一个字母, 所以看前兩個數字的 "dp 數量").
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        // dp[i] 表示, 取 i 个 digits, 可以有多少种组合.
        int[] dp = new int[len + 1];
        dp[0] = 1;
        // 0 开头的多位数不合法
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= len; i++) {
            // 每个dp[i] 有2种可能组成方式,
            // 1位数(i) 和 dp[i-1];
            // 或 2位数(i-1, i) 和 dp[i-2];
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            // 检查合法范围即可. 题目: 1-26 => A-Z
            if (oneDigit != 0)
                dp[i] += dp[i - 1];
            if (twoDigit >= 10 && twoDigit <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[len];
    }
}
