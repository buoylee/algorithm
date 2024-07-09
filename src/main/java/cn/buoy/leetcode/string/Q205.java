package cn.buoy.leetcode.string;

import java.util.HashMap;

public class Q205 {
    /**
     * 简单; 其實第2種寫法很簡潔, 但是思路不容易想, 但是還是好理解的.
     * 思路: 記錄 相同的 2個 char 是否 一一對應.
     * 例如:  s 的 'a' 對應 t 的 'b',
     * 也要檢查 t 的 'b' 是否也對應 s 的 'a'(因爲不反過來檢查, 可能造成 s 的 'a' 'c' 都對應 t 的 'c')
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        // 2個 map 存的其實是 同一對 key value, 只是 位置對調.
        HashMap<Character, Character> mapS = new HashMap<>();
        HashMap<Character, Character> mapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            // 檢查 同一對 key value 是否 是否真的 互相匹配.
            // s -> t
            if (mapS.containsKey(sc))
                if (mapS.get(sc) != tc)
                    return false;
                else
                    mapS.put(sc, tc);
            // t -> s
            if (mapT.containsKey(tc))
                if (mapT.get(tc) != sc)
                    return false;
                else
                    mapT.put(tc, sc);
        }
        return true;
    }

    /**
     * 這個方法很巧妙, 利用 i 來區分 不同的 char, 但不比在乎 到底是哪個 char. 只要 pattern 相同, 统计的 數量 就會一直相同.
     */
    public boolean isIsomorphic2(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 第一次進入, 都是 0, 所以肯定相等.
            if (m1[s.charAt(i)] != m2[t.charAt(i)])
                return false;
            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }
        return true;
    }
}
