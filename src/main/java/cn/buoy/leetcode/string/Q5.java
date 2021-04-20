package cn.buoy.leetcode.string;

public class Q5 {
    /**
     * https://www.youtube.com/watch?v=QhFkiwPLvHg&t=279s
     * 一般递归, 时空都不错, 还简单
     */
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    /**
     * https://www.youtube.com/watch?v=pi7ttqr1MT4&t=632s
     * 视频帮助理解
     * DP
     */
    public static String longestPalindrome2(String s) {
        int n = s.length();
        String res = null;
        int palindromeStartsAt = 0, maxLen = 0;

        boolean[][] dp = new boolean[n][n];
        // dp[i][j] indicates whether substring s starting at index i and ending at j is palindrome

        //这种2重遍历, 是从后到头扩展, 保证不会超过没求过的情况.
        //向前
        for (int i = n - 1; i >= 0; i--) { // keep increasing the possible palindrome string
            //向后
            for (int j = i; j < n; j++) { // find the max palindrome within this window of (i,j)

                //check if substring between (i,j) is palindrome
                //第一次只有1个字符, 肯定true
                dp[i][j] = (s.charAt(i) == s.charAt(j)) // chars at i and j should match
                        &&
                        //这里是上边2个字母夹着的str 的部分 是否是回文
                        //如果是 i == j; i, j; i, X, j 这3种情况, 只要 i, j 2位相等, 就必然相等.
                        (j - i < 3  // if window is less than or equal to 3, just end chars should match
                                //否则需要看 他们内一层是否是回文.
                                || dp[i + 1][j - 1]); // if window is > 3, substring (i+1, j-1) should be palindrome too

                //update max palindrome string
                if (dp[i][j] && (j - i + 1 > maxLen)) {
                    palindromeStartsAt = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(palindromeStartsAt, palindromeStartsAt + maxLen);
    }
}
