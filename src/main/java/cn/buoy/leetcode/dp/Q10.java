package cn.buoy.leetcode.dp;

public class Q10 {
    /**
     * 难! 一切不懂都要画出 2维的 str/pattern 图. 有空复习
     * https://www.youtube.com/watch?v=DqhPJ8MzDKM&t=3s
     * https://www.youtube.com/watch?v=c5vsle60Uw8
     * 思路: s 为 x轴, p 为 y轴, 从 dp[0][0] 到 dp[0][pi], 再外层循环 dp[1]~dp[si].
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示, s取 i len, p取 j len, 2者能否匹配.
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // init, 如果 s 是 '', dp[0][i] 只需要看 p 的 前2位是否 match 即可. 也就是 dp[0][j] = dp[0][j - 2].
        // 只要第一个不可能是 '*', 所以从 index1 开始
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*')
                dp[0][j] = dp[0][j - 2];
        }
        for (int si = 1; si <= m; si++) {
            for (int pi = 1; pi <= n; pi++) {
//                if (p.charAt(pi - 1) == '*') {
//                    dp[si][pi] = dp[si][pi - 2] || (dp[si - 1][pi] && (s.charAt(si - 1) == p.charAt(pi - 2) || p.charAt(pi - 2) == '.'));
//                } else {
//                    dp[si][pi] = dp[si - 1][pi - 1] && (s.charAt(si - 1) == p.charAt(pi - 1) || p.charAt(pi - 1) == '.');
//                }
                if (s.charAt(si - 1) == p.charAt(pi - 1) || p.charAt(pi - 1) == '.') // 这个简单, 如果 s[si] == p[pi] 或 p[pi] == '.', 只需要比较 s和p 前一位 dp 即可.
                    dp[si][pi] = dp[si - 1][pi - 1];
                else if (p.charAt(pi - 1) == '*') {
                    if (s.charAt(si - 1) == p.charAt(pi - 2) || p.charAt(pi - 2) == '.') { // 关键难点: p '*'前一位 和 s '当前位' 是 相等 或 '.' 的情况,
                        dp[si][pi] = dp[si][pi - 2] // 这里同样, '*' 可以当作 0 使用.
                                || dp[si - 1][pi]; // 关键: 当作 1个或多个 '*' 前 char 来使用. 例: s: acbbbb; p: acb*; 这个时候就是看 acbbb 能否和 "acb*"/"ac.*" 匹配
                    } else
                        dp[si][pi] = dp[si][pi - 2]; // 这个简单, 如果 '*' "前一个 char" 不同/不是 '.', 那当前这个 '*' 只能当 0 来用了, 所以, 由 dp[si][pi - 2] 决定.
                }
            }
        }
        return dp[m][n];
    }


    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) return false;
        // dp[i][j]表示, str 前i substr 与 pat 前j substr 是否正则匹配.
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        //两str都为null时, 初始为 true.
        dp[0][0] = true;
        //初始化第一行, str == null, pat 0~n-1, 如果出现*, 说明*前一位的字符可有可无, 只要 前2位的pat是true, 当前位 也可以是true.
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == '*' && dp[0][j - 1]) {
                dp[0][j + 1] = true;
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


    public boolean isMatch3(String s, String p) {
        return isMatch3(s, p, 0, 0, new Boolean[s.length()][p.length()]);
    }

    private boolean isMatch3(String s, String p, int si, int pi, Boolean[][] cache) {
        if (pi >= p.length()) return si >= s.length(); // both reaches to end
        if (si >= s.length()) return validPattern(p, pi);
        if (cache[si][pi] != null) return cache[si][pi];

        boolean result = false;
        boolean cMatch = s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.';
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') { // aab, c*a*b || aab, a*b
            result = isMatch3(s, p, si, pi + 2, cache) ||
                    cMatch && isMatch3(s, p, si + 1, pi, cache);
        } else if (cMatch) {
            result = isMatch3(s, p, si + 1, pi + 1, cache);
        }
        cache[si][pi] = result;

        return result;
    }

    // can only be a*b*.*, extra
    private boolean validPattern(String p, int i) {
        if (i + 1 < p.length() && p.charAt(i + 1) == '*')
            return validPattern(p, i + 2);
        return i == p.length();
    }
}
