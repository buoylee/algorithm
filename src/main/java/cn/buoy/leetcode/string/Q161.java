package cn.buoy.leetcode.string;

public class Q161 {
    /**
     * https://www.youtube.com/watch?v=nDstaiEUljY
     * 分情况判断,
     * length相同, 2 string 都跳过一位, 如果剩下都相同就true.
     * 长度不同, 跳过 长的string一位, 如果剩下都相同就true.
     */
    public boolean isOneEditDistance(String s, String t) {
        //遍历短的
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
                    return s.substring(i + 1).equals(t.substring(i + 1));
                else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
                    return s.substring(i).equals(t.substring(i + 1));
                else // s is longer than t, so the only possibility is deleting one char from s
                    return t.substring(i).equals(s.substring(i + 1));
            }
        }
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t
        //特别处理 如果前边都相等, 剩下最后一位的情况.
        //如果是相等, 那就不是 One Edit Distance 了.
        return Math.abs(s.length() - t.length()) == 1;
    }
}
