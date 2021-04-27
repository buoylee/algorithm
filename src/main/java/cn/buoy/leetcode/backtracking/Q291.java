package cn.buoy.leetcode.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q291 {
    public static void main(String[] args) {
        Q291 q291 = new Q291();
//        q291.wordPatternMatch("abca", "xxxyyzzxxx");
        q291.wordPatternMatch("abab", "redblueredblue");

    }

    /**
     * https://www.youtube.com/watch?v=f6Gd7AdegAs
     * 好抽象
     * <p>
     * 思路:
     * 遍历所有可能字母组合情况,
     * 遍历顺序用的深度遍历, 从刚match失败那一个pat字母回溯, 都不满足才 往上遍历上上个pat字母.
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    /**
     * @param str
     * @param i   表示str验到了那个index
     * @param pat
     * @param j   表示pat验到了那个index
     * @param map 用来存放pat对应的substr. 如果pat出现重复的字母, 取回当时的substr比较; 相同则进行下一个pat字母校验, 不同则回溯到上一个pat字母,改变后继续验证.
     * @param set 用来存放出现过的pat字母, 有则需要进行验证. 其实可以省略 用上边的map key 是否存在替代.
     * @return
     */
    boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
        // base case
        //当str, pat 都刚好结束, 则成功
        if (i == str.length() && j == pat.length()) return true;
        //排除掉上一行, 有1个先到尾, 则失败
        if (i == str.length() || j == pat.length()) return false;

        // get current pattern character
        char c = pat.charAt(j);

        // if the pattern character exists
        if (map.containsKey(c)) {
            String s = map.get(c);

            // then check if we can use it to match str[i...i+s.length()]
            if (!str.startsWith(s, i)) {
                return false;
            }

            // if it can match, great, continue to match the rest
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        // pattern character does not exist in the map
        //从下一个需要验证的str index开始, 遍历 每一种substr 对应 这个pat字母 的可能.(可不可能就看之前有没出现过这个pat字母, 如果暂时没出现, 会先跳过, 如果一直不重复出现则不用管, 直到 有其他重复的pat字母 与 之前相同的pat字母 不同时, 才会回溯调整.)
        //每次调整都先调上一步的, 直到上一步走到最后都没法满足, 才会再次往上.
        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);

            //看前边pat是否出现过这个字母
            if (set.contains(p)) {
                continue;
            }

            // create or update it
            //存放 pat 与 substr 映射
            map.put(c, p);
            //pat是否出现过某字母
            set.add(p);

            // continue to match the rest
            //深度遍历
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }

            // backtracking
            //回溯到上一个字母, 延长substr len 来作为这个字母的新str, 然后再次验证剩下的str.
            map.remove(c);
            set.remove(p);
        }

        // we've tried our best but still no luck
        return false;
    }
}
