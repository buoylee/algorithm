package cn.buoy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Q87 {
    /**
     * 還算好理解, 快速視頻, 代碼.
     * https://www.youtube.com/watch?v=Lq3Kr7-qXGI 視頻沒有做dp記憶化, 其他部分基本相同.
     * <p>
     * 思路: DP, 用完全相同的 all letter freq, 來確定 2個 substr 是否是 Scramble, 爲什麼可以, 因爲 遞歸 是由 最底層 1個 和 1個字母組成的substr, 直到 完整的 str.
     */
    Map<String, Boolean> map = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        //用 這2個 str, 作为dp key
        String key = s1 + s2;
        if (map.containsKey(key))
            return map.get(key);
        if (s1.equals(s2)) {
            map.put(key, true);
            return true;
        }
        // 通過比較 2個 str 的 所有 letter 出現次數
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            // 巧妙地使 同letter freq 相反, 如果相加等於0, 說明 letter 數量相同.
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        // 只要有一個letter 數量不同, 就 false.
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                map.put(key, false);
                return false;
            }
        }
        //递归 substr 任意分隔爲 2半, 看是否满足; 至少切一個 letter
        for (int i = 1; i < s1.length(); i++) {
            // 找到 左等左, 右等右; 例如: ab c, ab c
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) {
                map.put(key, true);
                return true;
            }
            // 或 找到 左等右, 右等左; 例如: ab c, c ab
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                map.put(key, true);
                return true;
            }
        }
        // 2種情況都不是, false
        map.put(key, false);
        return false;
    }
}
