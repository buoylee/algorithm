package cn.buoy.leetcode.string;

public class Q65 {
    /**
     * 太多需要判斷的例子, 其實背就簡單. 視頻, 代碼
     * https://www.youtube.com/watch?v=QXNvEz-GwQ4
     */
    public boolean isNumber(String s) {
        s = s.toLowerCase().trim();
        boolean dotSeen = false;
        boolean eSeen = false;
        boolean numberBeforeE = false;
        boolean numberAfterE = false;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if ('0' <= cur && cur <= '9') {
                if (!eSeen) numberBeforeE = true;
                if (eSeen) numberAfterE = true;
            } else if (cur == '-' || cur == '+') {
                // 關鍵: 只有e能出現在 +/- 的前一位, 不能是 '.', digit, ' '
                if (i != 0 && s.charAt(i - 1) != 'e') return false;
            } else if (cur == '.') { // '.' 只能出現在 e 前邊, 且 只能有 1個 '.'
                if (eSeen || dotSeen) return false;
                dotSeen = true;
            } else if (cur == 'e') { // 只能有 1個 'e'
                if (eSeen) return false;
                eSeen = true;
            } else { // invalid chars
                return false;
            }
        }
        return eSeen ? (numberBeforeE && numberAfterE) : numberBeforeE;
    }

    public boolean isNumber2(String s) {
        if (s == null || s.length() == 0) return false;
        s = s.trim();
        boolean seenDigit = false, seenDot = false, seenE = false;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '.') {
                if (seenDot || seenE) return false;
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                if (!seenDigit || seenE) return false;
                seenE = true;
                seenDigit = false;
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else {
                return false;
            }
        }
        return seenDigit;
    }
}
