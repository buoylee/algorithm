package cn.buoy.leetcode.dp;

public class Q44 {
    /**
     * 10, 比 10 稍微简单点. 有空复习
     * https://www.youtube.com/watch?v=9OnS06RYQiw
     * 還是看這個比較好, dp, 聽完思路, 看註釋
     * <p>
     * https://www.youtube.com/watch?v=-8QnRMyHo_o
     * 思路: 把 s 作为 x 轴, p 作为 y 轴, 构建 2维 dp, 关键在把 '*' 当作多个 char 使用时, 如何 转移状态(依赖哪个 dp).
     */
    public boolean isMatch(String str, String pat) {
        char[] strs = str.toCharArray();
        char[] pats = pat.toCharArray();
        int strLen = str.length();
        int patLen = pat.length();
        // 關鍵: dp[i][j] 表示, s取 i len, p取 j len, 2者能否匹配. 注意! 所以對應到字符 charAt 就需要 i-1, 和 j-1
        boolean[][] dp = new boolean[strLen + 1][patLen + 1];
        dp[0][0] = true; //case when both pattern & string are empty.
        // init, pattern 如果是 * 爲開頭的情況
        for (int i = 1; i <= patLen; i++) {
            if (pat.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 1];
        }
        // 从 dp[1] 到 dp[len], 一列一列
        for (int i = 1; i <= strLen; i++) {
            for (int j = 1; j <= patLen; j++) {
                if (strs[i - 1] == pats[j - 1] || pats[j - 1] == '?') {
                    // 当 "当前的字符" 相同, 或者p[i] == '?' 的时候, 对比 s/p 前一个 len 的 dp 就好.
                    // take value from diagonal
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pats[j - 1] == '*') {  // 当 pats[j-1] == '*' 的时候, 分2类对应下边代码,
                    // take OR of left or top value from dp.
                    // 難點: 一种是 'dp[i - 1][j]' 当做任意字符串; e.g. s: "1234"; p: "12*";
                    // 另一种 dp[i][j - 1] 当作 ''
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        // take the value form bottom-right corner.
        return dp[strLen][patLen];
    }
}
