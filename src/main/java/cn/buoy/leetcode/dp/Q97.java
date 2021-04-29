package cn.buoy.leetcode.dp;

public class Q97 {
    /**
     * https://www.youtube.com/watch?v=HmAF9xeS_2I
     */
    public boolean isInterleave(String s1, String s2, String s3) {

        char[] s1arr = s1.toCharArray();
        char[] s2arr = s2.toCharArray();
        char[] s3arr = s3.toCharArray();

        if (s3.length() != s1.length() + s2.length())
            return false;

        //dp[i][j]表示, s1 的 i位 substr , 和 s2 的 j位 substr, 可以组成 s3 i+j位的 交织str.
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i < s1.length() + 1; i++)
            for (int j = 0; j < s2.length() + 1; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = true;
                else if (i == 0)
                    //s1 为空时
                    dp[i][j] = (dp[i][j - 1] && s2arr[j - 1] == s3arr[i + j - 1]);
                else if (j == 0)
                    //s2 为空时
                    dp[i][j] = (dp[i - 1][j] && s1arr[i - 1] == s3arr[i + j - 1]);
                else
                    //只要 当前 i 或 j 与 s3 的 i+j 上的 char 相同时, 即满足.
                    dp[i][j] = (dp[i - 1][j] && s1arr[i - 1] == s3arr[i + j - 1]) || (dp[i][j - 1] && s2arr[j - 1] == s3arr[i + j - 1]);
            }

        return dp[s1.length()][s2.length()];
    }

}