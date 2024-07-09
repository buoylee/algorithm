package cn.buoy.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q49 {
    /**
     * 簡單, 視頻, 代碼, 下邊代碼處理 map 的key 稍微不同, 選一個就好.
     * https://www.youtube.com/watch?v=r05M73e30gA
     * 思路: 把不同 letter 順序的 word 轉爲 有序(字母順序) (視頻做法),
     * 或者 轉爲 有序(字母順序) 和 對應字母個數的arr,
     * 將 '產生的相同的 str' 放到同一個list下.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // 关键: 轉爲 26個字母 的 出現次數 arr
            char[] letterArr = new char[26];
            for (char c : s.toCharArray())
                letterArr[c - 'a']++;
            String arrStr = String.valueOf(letterArr);
            // arrStr 作爲 key, 原始 word list 作爲 value
            map.computeIfAbsent(arrStr, v -> new ArrayList<>()).add(s);
        }
        // 放入所有 word list
        return new ArrayList<>(map.values());
    }
}
