package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q3 {
    /**
     * https://www.youtube.com/watch?v=fBiiKy8kwaY
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                //关键: 因为 j 记录的 是a , 这次找到重复的可能是b, 所以要max 判断.
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
