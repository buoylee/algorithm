package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q159 {
    /**
     * 簡單, 視頻聽下思路就好, 下邊代碼處理更優更直觀.
     * https://www.youtube.com/watch?v=2vW_Q8ToSAU
     * 思路: 左右雙指針. 用 map 記錄 char 和 char的出現次數,
     * 每次 right 右移, 如果 map.size > 2, 則 left 右移, 直到 map.size 回到 <= 2,
     * 每次移動 left/right 後, 滿足 map.size <= 2, 則 判斷 maxLen.
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        // right++
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);
            // if map.size > 2, left++
            while (map.size() > 2) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0)
                    map.remove(leftChar);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // 上邊寫法更直觀, 跳過
    public int lengthOfLongestSubstringTwoDistinct2(String s) {
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
