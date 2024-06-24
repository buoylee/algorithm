package cn.buoy.leetcode.dp;

public class Q72 {
    /**
     * 還算好理解
     * https://www.youtube.com/watch?v=Uv9dNpHlSY4
     * 講的不是很清楚, 結合 https://www.youtube.com/watch?v=Q4i_rqON2-E 看.
     * 思路:
     * dp[i][j]定义: i 为 原str index = i , j 为 target str index = j,
     * dp[i][j] 表示, 原str 0-i 的substr 变化到 target str 0-j 的substr, 最少需要多少步.
     * 難點: 從dp[0][0], 輪替++ 開始思考就容易理解. 其实取的是目标格 的 左, 上, 左上, 这3種 '和 target 差一個字符' 情况的 minimum 然后++(例外, 當前格對於word1, word2相同, 則不需要++).
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        //其中一str为空, 另一str len时, 则需要 空 add 相同 len 步, 或 remove 相同 len 步 为空.
        //下列分别对应上述2种情况.
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int i = 1; i <= n; i++)
            dp[0][i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //这步好理解, 如果前 word1 到 word2 用n步完成, 且当前双方都加'相同的char', 那就没有增加需要处理的步数.
                // 所以這步肯定最小, 因爲不用 +1
                if (word1.charAt(i) == word2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                else {
                    //双方新加的char 不相同时, 3类情况:
                    // 1. 只有原str 多1 char; 原str多, remove;
                    // 2. 只有target str 多1 char; target str多, add;
                    // 3. 都多1 char; 同时增加char, 且不同(相同的情况, 在上方, 用最后一个char是否相同的方式 处理了.), replace.
                    // 都是 one step 的操作. 所以 ++.
                    int a = dp[i][j];
                    int b = dp[i][j + 1];
                    int c = dp[i + 1][j];
                    // 3者取最小 再+1
                    dp[i + 1][j + 1] = Math.min(Math.min(a, b), c) + 1;
                }
            }
        }
        return dp[m][n];
    }
}
