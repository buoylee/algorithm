package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q320 {
    /**
     * 简单, 听完思路, 直接看代码比较好理解.
     * https://www.youtube.com/watch?v=T8efp06SLTM
     * https://www.youtube.com/watch?v=fFcc-GZUIpU 短
     * 思路: backtracking, 每个 letter 都有2种选择, 1. 当作 数 累加; 2. 当作 char 直接加入 temp.
     * 实现, 分这2种情况 dfs,
     * 如果是作为 "数", 我们累计 numCount, 直到 "后续出现选择 letter 作为 char 使用" 或 "word 遍历结束" 时, 才 append numCount.
     * 如果是作为 "char", 先把 之前累计的 numCount append, 然后 append 当前 letter 即可.
     * 总结: 遇到 数, 只累计; 遇到 char, 先 append(numCount), 再 append(char).
     */
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        dfs(result, word, new StringBuilder(), 0, 0);
        return result;
    }

    /**
     * @param result
     * @param word
     * @param temp     组合str的临时状态.
     * @param numCount 到上次为止 转化为数字 的统计.
     * @param start
     */
    public void dfs(List<String> result, String word, StringBuilder temp, int numCount, int start) {
        // 先保存没操作前的 str 长度, 用于操作完回溯到此长度.
        int currLen = temp.length();
        // word 遍历结束
        if (start == word.length()) {
            // 如果 "当前 numCount" != 0, 记得 append(), 然后返回.
            if (numCount != 0) temp.append(String.valueOf(numCount));
            result.add(temp.toString());
            // backtracking
            temp.setLength(currLen);
            return;
        }
        // 关键: 情况1: 当前 index 的字母 当作 digit 累加. 因为不涉及 temp 修改, 不需要回溯.
        dfs(result, word, temp, numCount + 1, start + 1);
        // 关键: 情况2: 保留字母, 记得, 如果 numCount > 0 要先 append(numCount), 再 append 当前字母.
        if (numCount != 0) temp.append(String.valueOf(numCount));
        temp.append(word.charAt(start));
        // append 完 numCount, 记得归零 numCount.
        dfs(result, word, temp, 0, start + 1);
        // backtracking
        temp.setLength(currLen);
    }
}
