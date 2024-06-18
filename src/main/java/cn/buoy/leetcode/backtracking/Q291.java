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
     * (失效) https://www.youtube.com/watch?v=f6Gd7AdegAs
     * 好抽象, 其他视频都很不具体, 不如直接看代码
     * <p>
     * 思路: 主要依靠 strIndexStart 和 patIndexStart 能否同时走到最后来决定是否回溯, 中间通过对比 patLetter 和 startSubStr 唯一对应的关系, 来维持逻辑正确. 还算好理解.
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
     * @param completeStr
     * @param strIndexStart 表示str验到了那个index
     * @param completePat
     * @param patIndexStart 表示pat验到了那个index
     * @param letterStrMap  用来存放pat对应的substr. 如果pat出现重复的字母, 取回当时的substr比较; 相同则进行下一个pat字母校验, 不同则回溯到上一个pat字母,改变后继续验证.
     * @param subStrSet
     * @return
     */
    boolean isMatch(String completeStr, int strIndexStart, String completePat, int patIndexStart, Map<Character, String> letterStrMap, Set<String> subStrSet) {
        // base case
        // 当str, pat 都刚好结束, 则成功
        if (strIndexStart == completeStr.length() && patIndexStart == completePat.length()) return true;
        // 有1个先到尾, 则失败
        if (strIndexStart == completeStr.length() || patIndexStart == completePat.length()) return false;

        // get current pattern character
        char patLetter = completePat.charAt(patIndexStart);

        // if the pattern character exists
        // 关键: 如果有存在曾经的'patLetter'.
        if (letterStrMap.containsKey(patLetter)) {
            String strOfPatLetter = letterStrMap.get(patLetter);

            // then check if we can use it to match str[i...i+s.length()], 检查strIndexStart开始的completeStr是否满足 strOfPatLetter 作为前缀.
            if (!completeStr.startsWith(strOfPatLetter, strIndexStart)) {
                return false;
            }

            // if it can match, great, continue to match the rest, strIndexStart跳过Str.length, 继续检查.
            return isMatch(completeStr, strIndexStart + strOfPatLetter.length(), completePat, patIndexStart + 1, letterStrMap, subStrSet);
        }

        // 关键: 走到这里, 说明以下是 '不存在曾经的 patLetter' 的情况, pattern character does not exist in the map.
        // 从 strIndexStart 开始对比 subStr, 因为是未出现过的 patLetter, 遍历不同长度 substr, 直到找到新的 substr.
        // 关键: 每次都直到走到最后都没法满足, 才会回溯.
        for (int strIndex = strIndexStart; strIndex < completeStr.length(); strIndex++) {
            String startSubStr = completeStr.substring(strIndexStart, strIndex + 1);

            // 关键: 看前边是否出现过这个 subStr, 为什么跳过曾经出现过的 subStr? 因为在for开始前, 已经检查过与以往所有的 patLetter 是不同的, 所以, 也不可能用曾经出现过的 subStr.
            if (subStrSet.contains(startSubStr)) {
                continue;
            }

            // create or update it
            // 直至找到新的substr, 存放新的 patLetter 与新的 substr 映射
            letterStrMap.put(patLetter, startSubStr);
            subStrSet.add(startSubStr);

            // continue to match the rest
            //深度遍历, 每一次都要走到最后, 发现base case不满足, 才会回溯.
            if (isMatch(completeStr, strIndex + 1, completePat, patIndexStart + 1, letterStrMap, subStrSet)) {
                return true;
            }

            // backtracking
            letterStrMap.remove(patLetter);
            subStrSet.remove(startSubStr);
        }

        // we've tried our best but still no luck
        return false;
    }
}
