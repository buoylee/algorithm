package cn.buoy.leetcode.string;

public class Q115 {
    /**
     * 視頻, 代碼, 還是比較容易理解, 視頻, 畫圖, 就好理解了.
     * https://www.bilibili.com/video/BV1R4411T7sV?from=search&seid=16966281719191650236
     * 思路: 2緯 dp, dp[i][j], j 表示 可以匹配到 target 的 0~j 的字符; i 表示 檢查到 long str 的 index;
     * dp[i][j] 表示, 到達 這個位置的 target 最多幾個.
     * 例:
     * 0 1 2 3 4 5
     * a b c a a b,
     * target: a b;
     * 第 2 個 b 是由,
     * 第一部分: 1個 連續 (a b); (和 第2 部分一樣, 只不過是由 前邊的 b 和 這個b 前邊的所有 a 累積下來的 target數)
     * 第二部分: 3 個不同的 a 和 最後的 b 一組. (本步)
     * 如果要找 target, 就要找 target,substr(0, len - 1)(去掉最後一位), 就是上邊寫的"第二部分" + 累積("第一部分")
     * 所以, dp[i][j] = dp[i - 1][j](第一部分) + dp[i - 1][j - 1](第二部分)
     * 注意, 如果 i 走到 不同於 target 最後一位字符時, dp[i][j] = dp[i - 1][j](第一部分, 前邊所累積的target), 因爲只有出現 '末位相同' 才會 再次累積 target.
     */
    public int numDistinct(String s, String t) {
        // s 當 x座標, t 當 y 座標
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        // initialize the dp value when t is an empty string, number of subsequence of an empty string should be 1
        // 如果 target 是 ' ', 總會有 "一個字符都不選"(dp[i][0] 表示 任何 longStr, 但是 target 是 ' ') 這 1個 答案.
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;
        // 遍歷 target 的 所有 pre 長度. 從 1個字符開始.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //in both cases, the subsequence in String t should be ending with character t.charAt(j - 1)
                //注意, 这里的 charAt(i - 1) 其实和 dp[i] 是同一個arr元素.
                if (s.charAt(i - 1) == t.charAt(j - 1))
                    // when two pointers pointing to same character
                    // if we take these two characters simultaneously, we should have dp[i-1][j-1] subsequences
                    // otherwise if we overlook current i (moving back for one step) and keeping the current j we have another dp[i -1][j]
                    // 上一個pre(target 少最後一個字符) 到達 j-1 時的 數量(和當前 字符組成 target) + 前邊累積的 target 數
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else
                    // when two pointers pointing to difference characters
                    //we cannot take these two characters but we still should make j ending with pointing to current position
                    // then we should move i backward
                    // 沒有 滿足 target 最後一位, 只能 前邊累積的 target 數.
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[m][n];
    }
}
