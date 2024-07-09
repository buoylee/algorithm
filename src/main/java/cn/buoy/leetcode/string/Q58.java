package cn.buoy.leetcode.string;

public class Q58 {
    /**
     * 超簡單, 不用想太複雜的方法, 視頻, 代碼
     * https://www.youtube.com/watch?v=QO3LXVESGbk
     * 思路: 1. 先跳過 str末尾的空格; 2. 再統計 str末尾 '非空格字符', 並統計 '末尾 word' 的長度.
     */
    public int lengthOfLastWord(String s) {
        int len = s.length(), lastLength = 0;
        //先去str最后的空格
        while (len > 0 && s.charAt(len - 1) == ' ')
            len--;
        //再去非空格, 並統計 '末尾 word' 的長度.
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
