package cn.buoy.leetcode.dp;

public class Q44 {
    /**
     * https://www.youtube.com/watch?v=9OnS06RYQiw
     * 還是看這個比較好, dp, 聽完思路, 看註釋
     * <p>
     * https://www.youtube.com/watch?v=-8QnRMyHo_o
     */
    public boolean isMatch(String str, String pat) {

        char[] chars = str.toCharArray();
        char[] pats = pat.toCharArray();
        int strLen = str.length();
        int patLen = pat.length();
        // 關鍵: dp[i][j] 表示的是, str 重頭包含i個字符, pat 重頭包含j個字符. 注意! 所以對應到字符 charAt 就需要 i-1, 和j-1
        boolean[][] dp = new boolean[strLen + 1][patLen + 1];
        dp[0][0] = true; //case when both pattern & string are empty.

        // pattern 如果是 * 爲開頭的情況
        for (int i = 1; i <= patLen; i++) {
            if (pat.charAt(i - 1) == '*')
                dp[0][i] = dp[0][i - 1];
        }

        for (int i = 1; i <= strLen; i++) {
            for (int j = 1; j <= patLen; j++) {
                if (chars[i - 1] == pats[j - 1] || pats[j - 1] == '?') {
                    //当 当前的字符相同, 或者p[i] == '?' 的时候, 对比 2个str 都去掉最后一个字符的 dp[i][j] 就好.
                    //take value from diagonal
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pats[j - 1] == '*') {
                    //take OR of left or top value from dp.
                    //当 pats[j-1] == '*' 的时候, 分2类对应下边代码,
                    // 難點: 一种是'dp[i - 1][j]'当做任意字符串(当做与str最后一个字符相同); e.g. [1,2,3,4] [1,2,*]
                    // 另一种 dp[i][j - 1] 是当作`空字符`.
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        //take the value form bottom-right corner.
        return dp[strLen][patLen];
    }
}
