package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q159 {
    /**
     * https://www.youtube.com/watch?v=2vW_Q8ToSAU
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() < 1) return 0;
        HashMap<Character, Integer> index = new HashMap<Character, Integer>();
        int lo = 0;
        int hi = 0;
        int maxLength = 0;
        while (hi < s.length()) {
            //只要是不同指的数量小于等于2, 就可以放入, 放入的是某个字母的最后遇到的index.
            //关键: 因为如果要排除掉某个字母的所有index时, 其实就是要排掉最后一次遇到的index及之前的index.
            if (index.size() <= 2) {
                char c = s.charAt(hi);
                index.put(c, hi);
                hi++;
            }
            if (index.size() > 2) {
                int leftMost = s.length();
                //找到 index 中 最靠前的 最后出现该的字母的位置.
                for (int i : index.values()) {
                    leftMost = Math.min(leftMost, i);
                }
                char c = s.charAt(leftMost);
                index.remove(c);
                //+1 才是合法的位置
                lo = leftMost + 1;
            }
            maxLength = Math.max(maxLength, hi - lo);
        }
        return maxLength;
    }
}
