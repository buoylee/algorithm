package cn.buoy.leetcode.backtracking;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q291 {
    /**
     * 算简单, 其他视频都很不具体, 不如直接看代码
     * (失效) https://www.youtube.com/watch?v=f6Gd7AdegAs
     * https://www.youtube.com/watch?v=ZlQaple60l4&t=1s
     * 思路: 遍历所有 subStr 和 pattern char 所有组合. 根据 strIndexStart 和 patIndexStart 能否同时走到最后来决定是否回溯, 中间通过对比 patLetter 和 startSubStr 唯一对应的关系, 来维持逻辑正确. 还算好理解.
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> charToSubStrMap = new HashMap<>();
        Set<String> subStrSet = new HashSet<>();
        return dfs(str, 0, pattern, 0, charToSubStrMap, subStrSet);
    }

    /**
     * @param wholeStr
     * @param strIdxStart  表示 str 验到了 哪个 index
     * @param wholePat
     * @param patIdxStart  表示 pat 验到了 哪个 index
     * @param letterStrMap 用来存放 pat 对应的 substr. 如果 pat 出现重复的字母, 取出 map 中 "对应的 substr" 比较; 相同则进行下一个 pat char 校验, 不同则回溯到上一个 pat char,改变后继续验证.
     * @param subStrSet    用于快速排除 "出现过的 subStr"
     * @return
     */
    boolean dfs(String wholeStr, int strIdxStart, String wholePat, int patIdxStart, Map<Character, String> letterStrMap, Set<String> subStrSet) {
        // 当str, pat 都刚好结束, 则成功
        if (strIdxStart == wholeStr.length() && patIdxStart == wholePat.length()) return true;
        // 排除上边同时到尾, 有其一先到尾, 则失败
        if (strIdxStart == wholeStr.length() || patIdxStart == wholePat.length()) return false;
        // get current pattern char
        char patChar = wholePat.charAt(patIdxStart);
        // if the pattern char exists
        // 关键: 处理 patChar 出现过的情况, 检查是否与 曾经出现过的 "patChar 和 subStr 的配对" 匹配.
        if (letterStrMap.containsKey(patChar)) {
            String strOfPatChar = letterStrMap.get(patChar);
            // then check if we can use it to match str[i...i+s.length()], 检查 strOfPatChar 是否是 strIdxStart 作为起点的 wholeStr 的 preStr.
            if (!wholeStr.startsWith(strOfPatChar, strIdxStart))
                return false;
            // if it can match, great, continue to match the rest, strIndexStart跳过Str.length, 继续检查.
            return dfs(wholeStr, strIdxStart + strOfPatChar.length(), wholePat, patIdxStart + 1, letterStrMap, subStrSet);
        }

        // 关键: 处理 '未出现过的 patChar' 的情况, pattern character does not exist in the map.
        // 从 strIdxStart 开始对比 subStr, 因为是未出现过的 patChar, 遍历不同长度 substr, 找到 "从未出现过的 substr" 就继续 dfs
        // 关键: 每次都直到走到最后都没法满足, 才会回溯.
        for (int strIndex = strIdxStart; strIndex < wholeStr.length(); strIndex++) {
            String startSubStr = wholeStr.substring(strIdxStart, strIndex + 1);
            // 排除 "出现过的 subStr".
            if (subStrSet.contains(startSubStr)) continue;
            // create or update it
            // 记录 "新的 patChar" 与 "新的 substr" 对应关系
            letterStrMap.put(patChar, startSubStr);
            subStrSet.add(startSubStr);
            // continue to match the rest
            // 每一次都要走到最后, 发现 base case 不满足, 才会回溯.
            if (dfs(wholeStr, strIndex + 1, wholePat, patIdxStart + 1, letterStrMap, subStrSet))
                return true;
            // backtracking
            letterStrMap.remove(patChar);
            subStrSet.remove(startSubStr);
        }
        // we've tried our best but still no luck
        return false;
    }

    public static void main(String[] args) {
        Q291 q291 = new Q291();
//        q291.wordPatternMatch("abca", "xxxyyzzxxx");
        q291.wordPatternMatch("abab", "redblueredblue");

    }
}
