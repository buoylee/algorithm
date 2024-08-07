package cn.buoy.leetcode.dp;

public class Q97 {
    /**
     * 直接看視頻. 還是相對容易理解的
     * https://www.youtube.com/watch?v=HmAF9xeS_2I
     * https://www.youtube.com/watch?v=rNC9u7nuf8c 可以先看這個
     * 思路: dp[i][j]表示, s1 取前i位 substr, 和 s2 取前j位 substr, 能否组成 s3 前i+j位 str.
     * 走到 s3arr(dp[i][j]), 最後一步有2種走法, dp[i - 1][j] 或 dp[i][j - 1], 哪个 arr 延伸出来的 char 可以 == s3subarr 的最後一個字母, 就可以选择这个组合,
     * 所有有 3种情况:
     * 单 dp[i - 1][j]
     * 单 dp[i][j - 1]
     * dp[i - 1][j] + dp[i][j - 1]
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] s1arr = s1.toCharArray();
        char[] s2arr = s2.toCharArray();
        char[] s3arr = s3.toCharArray();
        if (s3.length() != s1.length() + s2.length()) return false;
        // dp[i][j]表示, s1 取前i位 substr, 和 s2 取前j位 substr, 能否组成 s3 前i+j位 str.
        // 細節: 所以, 對應到 s1subarr 和 s2subarr 最后的字母 應該是 index: i-1 和 index: j-1.
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length() + 1; i++)
            for (int j = 0; j < s2.length() + 1; j++) {
                if (i == 0 && j == 0) // init 原点
                    dp[i][j] = true;
                else if (i == 0) // 初始化列
                    dp[i][j] = (dp[i][j - 1] && s2arr[j - 1] == s3arr[i + j - 1]);
                else if (j == 0) // 初始化行
                    dp[i][j] = (dp[i - 1][j] && s1arr[i - 1] == s3arr[i + j - 1]);
                else
                    // 因爲走到 s3arr(dp[i][j]), 最後一步有2種走法, dp[i - 1][j] 或 dp[i][j - 1] , 當 s3arr 最後一個字母 和 s1arr/s2arr 都相同時, 就都要考慮.
                    // 当 s1arr 最後一位字母(s1arr[i - 1]) == s3arr 最後一位字母(s3arr[i + j - 1]), 就要檢查 dp[i - 1][j]
                    // 当 s2arr 最後一位字母(s2arr[j - 1]) == s3arr 最後一位字母(s3arr[i + j - 1]), 就要檢查 dp[i][j - 1]
                    dp[i][j] = (dp[i - 1][j] && s1arr[i - 1] == s3arr[i + j - 1]) || (dp[i][j - 1] && s2arr[j - 1] == s3arr[i + j - 1]);
            }
        return dp[s1.length()][s2.length()];
    }

}