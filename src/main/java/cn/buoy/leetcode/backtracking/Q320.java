package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q320 {
    /**
     * https://www.youtube.com/watch?v=T8efp06SLTM
     * 听完思路, 直接看代码比较好理解.
     */
    public List<String> res = new ArrayList<>();

    public List<String> generateAbbreviations(String word) {
        help(word, new StringBuilder(), 0, 0);
        return res;
    }

    /**
     * @param word
     * @param temp     组合str的临时状态.
     * @param numCount 到上次为止 转化为数字 的统计.
     * @param start
     */
    public void help(String word, StringBuilder temp, int numCount, int start) {
        //先保存没操作前的str长度, 用于操作完回溯到此长度.
        int currentLength = temp.length();
        //到单词结尾结束
        if (start == word.length()) {
            //如果前边有count没输出, 先输出, 再加上当前index 字母, 最后返回.
            if (numCount != 0) temp.append(String.valueOf(numCount));
            res.add(temp.toString());
            temp.setLength(currentLength);
            return;
        }
        //这是一种情况, 将当前index的字母 转为 digit 累加. 所以 temp 未改变, 不需要回溯.
        help(word, temp, numCount + 1, start + 1);

        //这是另一种情况, 保留字母, 记得如果之前有统计数字 count >0 要先填上再插入当前字母.
        if (numCount != 0) temp.append(String.valueOf(numCount));
        temp.append(word.charAt(start));
        //重置 count
        help(word, temp, 0, start + 1);
        //backtracking temp.append(word.charAt(i))
        temp.setLength(currentLength);
    }
}
