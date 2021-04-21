package cn.buoy.leetcode.string;

public class Q392 {
    /**
     * https://www.youtube.com/watch?v=LVablZAfKhI
     * 简单解法: 按顺序找每个substr字母, 长str沿着往下找, 直到, str找到 或 到 长str结尾.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int indexS = 0, indexT = 0;
        while (indexT < t.length()) {
            if (t.charAt(indexT) == s.charAt(indexS)) {
                indexS++;
                if (indexS == s.length()) return true;
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
