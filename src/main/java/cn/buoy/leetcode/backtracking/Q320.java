package cn.buoy.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Q320 {
    /**
     * https://www.youtube.com/watch?v=T8efp06SLTM
     */
    public List<String> res = new ArrayList<>();

    public List<String> generateAbbreviations(String word) {
        help(word, new StringBuilder(), 0, 0);
        return res;
    }

    /**
     * @param word
     * @param temp  组合str的临时状态.
     * @param count 到上次为止 转化为数字 的统计.
     * @param i
     */
    public void help(String word, StringBuilder temp, int count, int i) {
        //先保存没操作前的str长度, 操作完, 回溯到这.
        int n = temp.length();
        //到单词结尾结束
        if (i == word.length()) {
            //如果前边有count没输出, 先输出, 再加上当前index 字母, 最后返回.
            if (count != 0) temp.append(String.valueOf(count));
            res.add(temp.toString());
            temp.setLength(n);
            return;
        }
        //这是一种情况, 将当前index 字母 转为 digit 累加.
        help(word, temp, count + 1, i + 1);
        //这是另一种情况, 保留字母, 记得如果之前有统计数字 count >0 要先填上再插入当前字母.
        if (count != 0) temp.append(String.valueOf(count));
        temp.append(word.charAt(i));
        //重置 count
        help(word, temp, 0, i + 1);
        //backtracking temp.append(word.charAt(i))
        temp.setLength(n);
    }
}
