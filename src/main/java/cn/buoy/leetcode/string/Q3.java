package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q3 {
    /**
     * 簡單
     * https://www.youtube.com/watch?v=fBiiKy8kwaY
     * 思路: 當出現有相同字母的時候, 要去掉前邊所有的substr.
     */
    public int lengthOfLongestSubstring(String s) {
        // map<字母 , 這個字母的index位置>
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            // 遇到同字母,
            if (map.containsKey(letter))
                // 先取上一個相同字母的index, +1;
                // 但是, 之前可能有其他重复的字母, 已经截取更右的index了, 所以需要再次比较left.
                left = Math.max(left, map.get(letter) + 1);
            result = Math.max(result, i - left + 1);
            map.put(letter, i);
        }
        return result;
    }

    public static void main(String[] args) {
        new Q3().lengthOfLongestSubstring("abba");
    }
}
