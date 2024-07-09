package cn.buoy.leetcode.string;

public class Q242 {
    /**
     * 超簡單, 視頻
     * https://www.youtube.com/watch?v=vEPf77JYNq8
     * 思路: 2个 str 分别对 freq ++ 和 --, 只有当 count[i] 出现 != 0 的情况, 才说明各自的 char 数量不同.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        // 检查有没有数量不同的 char
        for (int i : count) {
            if (i != 0)
                return false;
        }
        return true;
    }

    // 上边的比较直观
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] freqs = new int[26];
        for (char c : s.toCharArray()) freqs[c - 'a']++;
        // --freqs[c - 'a'] < 0; 其实是表达了, 出现了 不该出现的 char; 例如 次数超过, 上边不存在的
        // 有没可能出现 最终 freqs[c - 'a'] > 0 呢? 因为上边判断了 s.length() == t.length(), 所以, 如果出现 > 0 就肯定会出现上边的例子的情况.
        for (char c : t.toCharArray()) if (--freqs[c - 'a'] < 0) return false;
        return true;
    }
}
