package cn.buoy.leetcode.dp;

public class Q72 {
    /**
     * 還算好理解
     * https://www.youtube.com/watch?v=Uv9dNpHlSY4
     * 講的不是很清楚, 結合 https://www.youtube.com/watch?v=Q4i_rqON2-E 看.
     * 思路: dp[i][j] 表示, "原 str" 前i个字符 substr 变化到 "target str" 前j个字符 substr, 最少需要多少步.
     * 難點: 從 dp[0][0] 开始, i/j 輪替++, 这样思考就容易理解. 其实取的是目标格 的 左, 上, 左上, 这3種 '与 target 差一個字符' 情况下, 选 minimum, 然后+1.
     * 最后一种情况是: "原 str" 和 "target str" 都增加了一位相同的 char, 此时, 并不会在 原 dp 上增加步数, dp[i + 1][j + 1] = dp[i][j]
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // init: "其中一 str" 为空, "另一 str" 长度为 len 时, 则需要 "空 str" add "相同 len 步", 或 remove "相同 len 步" 为 "空 str" 使得 2 word 相同.
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 1; i <= n; i++)
            dp[0][i] = i;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 关键: 如果当前双方都加 '相同的 char', "前 word1" 到 "前 word2" 用n步完成, 则没有增加需要处理的步数. 还是 n
                if (word1.charAt(i) == word2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                else {
                    // 关键: 双方 "新加的 char 不相同" 时, 3类情况:
                    // 1. 只有 "原 str" 多1 char; 需要1步 remove;
                    // 2. 只有 "target str" 多1 char; 需要1步 add;
                    // 3. "原 str" 和 "target str" 都多1 "互不不同 char"; 需要1步 replace.
                    // 都是 one step 的操作. 所以 ++.
                    int a = dp[i + 1][j];
                    int b = dp[i][j + 1];
                    int c = dp[i][j];
                    // 关键: 3者取最小, 再+1
                    dp[i + 1][j + 1] = Math.min(Math.min(a, b), c) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
