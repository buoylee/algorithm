package cn.buoy.leetcode.string;

public class Q392 {
    /**
     * https://www.youtube.com/watch?v=LVablZAfKhI
     * https://www.youtube.com/watch?v=6tldFGiSxyE 短
     * 简单解法: 短 str 一個個 char 在 長 str 中 往下找.
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length())
                    return true;
            }
            indexT++;
        }
        return false;
    }

    /**
     * 2分查找, 思路: 统计出不同字母的index, 然后 遍历substr, 2分找 前字母所选index后的 字母出现的第一个位置.
     * DP, todo
     * https://www.bilibili.com/video/BV19f4y197yS?from=search&seid=12989273563261482543
     */
}
