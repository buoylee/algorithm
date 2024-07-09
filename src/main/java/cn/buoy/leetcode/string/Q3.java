package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q3 {
    /**
     * 簡單, 關鍵點, 要想清楚.
     * https://www.youtube.com/watch?v=fBiiKy8kwaY
     * 思路: 當出現有相同字母的時候, 要去掉前邊所有的substr.
     */
    public int lengthOfLongestSubstring(String s) {
        // map<字母 , 這個字母的index位置>
        HashMap<Character, Integer> map = new HashMap<>();
        int maxSubstrLen = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            // 遇到同字母,
            if (map.containsKey(letter))
                // 取相同字母的上一個index +1, 但是要取至今最大那個.
                // 關鍵: 思考上, 很簡單, 就是 上一個相同字母的下一個,
//                if (map.get(letter) + 1 > left)
//                    left = map.get(letter) + 1;
                // 這個寫法有點反應不過來.
                left = Math.max(left, map.get(letter) + 1);
            maxSubstrLen = Math.max(maxSubstrLen, i - left + 1);
            map.put(letter, i);
        }
        return maxSubstrLen;
    }

    public static void main(String[] args) {
        new Q3().lengthOfLongestSubstring("abba");
    }
}
