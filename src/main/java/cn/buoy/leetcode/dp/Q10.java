package cn.buoy.leetcode.dp;

public class Q10 {
    /**
     * https://www.youtube.com/watch?v=DqhPJ8MzDKM&t=3s
     * 难!
     * 关键在 转义方程, 需要用到 dp[i + 1][j + 1] 的 dp[i][j], dp[i + 1][j - 1], dp[i][j + 1], dp[i + 1][j - 1]的情况.
     */
    public boolean isMatch(String s, String p) {

        if (s == null || p == null) {
            return false;
        }
        //dp[i][j]表示, str 前i substr 与 pat 前j substr 是否正则匹配.
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //两str都为null时, 初始为 true.
        dp[0][0] = true;
        //初始化第一行, str == null, pat 0~n-1, 如果出现*, 说明*前一位的字符可有可无, 只要 前2位的pat是true, 当前位 也可以是true.
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {

                //分4大类: `.`, `*`, `相同`, `不同`.
                //如果有`.`, 可以忽略对比当前i, j, 取决于 i-1, j-1
                if (p.charAt(j) == '.') {
                    //这个是当前点的dp
                    dp[i + 1][j + 1] = dp[i][j];
                }
                //相等, 同上.
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    //如果是不`.*`的情况, 例如: 1234, 1235*
                    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
                        //等于pat的最后2位都没法增加匹配长度, 与pat去掉最后2位时的dp一致.
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    } else {
                        //123 符合 (123* 或 12.*), (12.* 有2种情况), (如果`.`!= 3, 则12符合12.), (123符合12.)
                        dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                    }
                }
                //都不符合, 维持初始false就好.
            }
        }
        return dp[s.length()][p.length()];
    }


    public boolean isMatch2(String s, String p) {
        return isMatch(s, p, 0, 0, new Boolean[s.length()][p.length()]);
    }

    private boolean isMatch(String s, String p, int si, int pi, Boolean[][] cache) {
        if (pi >= p.length()) return si >= s.length(); // both reaches to end
        if (si >= s.length()) return validPattern(p, pi);
        if (cache[si][pi] != null) return cache[si][pi];

        boolean result = false;
        boolean cMatch = s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.';
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') { // aab, c*a*b || aab, a*b
            result = isMatch(s, p, si, pi + 2, cache) ||
                    cMatch && isMatch(s, p, si + 1, pi, cache);
        } else if (cMatch) {
            result = isMatch(s, p, si + 1, pi + 1, cache);
        }
        cache[si][pi] = result;

        return result;
    }

    // can only be a*b*.*, extra
    private boolean validPattern(String p, int i) {
        if (i + 1 < p.length() && p.charAt(i + 1) == '*') return validPattern(p, i + 2);

        return i == p.length();
    }
}
