package cn.buoy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Q340 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=XMXIX8kNknA
     * https://www.youtube.com/watch?v=qficje_8OlI 短
     * 思路: 直接能想到, 左右雙指針, 用 map 來存 char: index, map size < k, 右++; map size > k 左++,
     * 關鍵是怎麼處理 左index, 直觀地想到是去掉 head 的 char,
     * 但是關鍵在 head 的 char 如果是 重複元素呢? 有 2種可能:
     * 1. aabcd, 刪 aa 即可.
     * 2. abacd, 只需要刪到 ab.
     * 3. 但是 b也可能是重複, 例如: abbacde, 這樣就要刪到 abb.
     * 所以, 關鍵要看出規律, 我們要刪到 最早把 "某個 char" 全部刪完 爲止.
     * 所以, map 中 我們只需要關注 "檢查過的index內" 某個 char 的最後一個index, 選最小的那個, 刪到這個 index 即可. 具體看代碼.
     * <p>
     * 本解法, 思路一樣, 不同在 "左index" 移動方法比較直觀, 上邊註釋的實現在下一個解法.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        int maxLeft = 0, curr = 0, maxLen = 0;
        // 統計 char 的 "出現次數"
        Map<Character, Integer> map = new HashMap<>();
        while (curr < s.length()) {
            char currChar = s.charAt(curr);
            map.put(currChar, map.getOrDefault(currChar, 0) + 1);
            // 關鍵: 超過就從head開始刪, 直到 map.size == k
            while (map.size() > k) {
                char leftChar = s.charAt(maxLeft);
                map.put(leftChar, map.get(leftChar) - 1);
                // freq爲0, 移除 map 中的 char
                if (map.get(leftChar) == 0)
                    map.remove(leftChar);
                maxLeft++;
            }
            maxLen = Math.max(maxLen, curr - maxLeft + 1);
            curr++;
        }
        return maxLen;
    }

    /**
     * 這個是註釋寫的實現, 思路比較精髓. 直觀的看上一個解法.
     */
    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int size = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);

            if (map.size() > k) {
                // find smallest index
                int sIndex = i;
                for (int index : map.values()) {
                    sIndex = Math.min(sIndex, index);
                }

                start = sIndex + 1;
                map.remove(s.charAt(sIndex));
            }

            size = Math.max(size, i - start + 1);
        }

        return size;
    }
}
