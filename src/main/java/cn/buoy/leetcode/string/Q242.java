package cn.buoy.leetcode.string;

public class Q242 {
    /**
     * 如果字母数不一样, 直接排除,
     * 如果出现不同数量的字母, 减完后肯定会出现 负数的 频率.
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] freqs = new int[26];
        for (char c : s.toCharArray()) freqs[c - 'a']++;
        for (char c : t.toCharArray()) if (--freqs[c - 'a'] < 0) return false;
        return true;
    }
}
