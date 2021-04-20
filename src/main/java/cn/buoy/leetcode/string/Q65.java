package cn.buoy.leetcode.string;

public class Q65 {
    /**
     * https://www.youtube.com/watch?v=QXNvEz-GwQ4
     *
     * @param s
     * @return
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
                if (i != 0 && s.charAt(i - 1) != 'e') return false;
            } else if (cur == '.') {
                if (eSeen || dotSeen) return false;
                dotSeen = true;
            } else if (cur == 'e') {
                if (eSeen) return false;
                eSeen = true;
            } else { // invalid chars
                return false;
            }
        }
        return eSeen ? (numberBeforeE && numberAfterE) : numberBeforeE;
    }
}
