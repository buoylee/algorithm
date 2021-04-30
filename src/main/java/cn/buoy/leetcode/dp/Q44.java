package cn.buoy.leetcode.dp;

public class Q44 {
    /**
     * https://www.youtube.com/watch?v=9OnS06RYQiw
     */
    public boolean isMatch(String s, String p) {

        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true; //case when both pattern & string are empty.

        for (int i = 1; i <= pLen; i++) {
            if (p.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 1];
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '?') {
                    //当 当前的字符相同, 或者p[i] == '?' 的时候, 对比 2个str 都去掉最后一个字符的 dp[i][j] 就好.
                    //take value from diagonal
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    //take OR of left or top value from dp.
                    //当 p[j] == '*' 的时候, 分2类对应下边代码, 一种是当做任意字符串(当做与str最后一个字符相同), 一种是当作`空字符`.
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        //take the value form bottom-right corner.
        return dp[sLen][pLen];
    }
}
