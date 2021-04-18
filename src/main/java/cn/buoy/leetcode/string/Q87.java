package cn.buoy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

public class Q87 {
    /**
     * https://www.youtube.com/watch?v=Lq3Kr7-qXGI
     * DP!
     */
    Map<String, Boolean> map = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
        sb.append(s2);
        //用2个比较的string, 作为dp key
        String key = sb.toString();

        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (s1.equals(s2)) {
            map.put(key, true);
            return true;
        }

        //类似 字谜, 统计频率
        //不能抵消, 不满足要求.
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                map.put(key, false);
                return false;
            }
        }

        //递归 substring, 判断是否满足, 左等左, 右等右; 或 左等右, 右等左(前者都是一个string, 后者是另一个string);
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) {
                map.put(key, true);
                return true;
            }

            if (isScramble(s1.substring(0, i), s2.substring(s1.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s1.length() - i))) {
                map.put(key, true);
                return true;
            }
        }

        map.put(key, false);
        return false;
    }
}
