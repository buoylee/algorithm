package cn.buoy.leetcode.string;

public class Q205 {
    /**
     * 简单
     * 善用了 i 的本来的顺序, 统计不同字母个数, 只关心当前顺序的字母频率. (不用关心具体字母, 只需区分不同)
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
