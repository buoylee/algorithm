package cn.buoy.leetcode.string;

public class Q58 {
    /**
     * https://www.youtube.com/watch?v=QO3LXVESGbk
     */
    public int lengthOfLastWord(String s) {
        int len = s.length(), lastLength = 0;
        //先去最后的空格
        while (len > 0 && s.charAt(len - 1) == ' ') {
            len--;
        }
        //再去非空格
        while (len > 0 && s.charAt(len - 1) != ' ') {
            lastLength++;
            len--;
        }

        return lastLength;
    }

    public int lengthOfLastWord2(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
}
