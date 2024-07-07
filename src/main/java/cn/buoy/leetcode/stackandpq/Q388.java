package cn.buoy.leetcode.stackandpq;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Q388 {
    /**
     * 簡單, 視頻,
     * https://www.youtube.com/watch?v=9ndZEOEjU2k
     * https://www.youtube.com/watch?v=ffHsxy3lLJk 短
     * 思路: 用 "\n"分 文件夾層, 用 "\t"分 來判斷第幾層, stack 保存 層的str, 當 出現 層數較低的str, 需要彈出, 直到比他小
     */
    public int lengthLongestPath(String input) {
        Stack<String> stack = new Stack<>();
        // 最長str長度
        int max = 0;
        // 當前段 str長度
        int currStrLen = 0;
        // "\n" 分 文件夾層
        for (String substr : input.split("\n")) {
            // paths.length == 層數(0開頭), "\n"分後, 用"\t"分, 只剩 文件名.
            String[] paths = substr.split("\t");
            String pathOrFileName = paths[paths.length - 1];
            // 當前str層數 和 現在實際層數 比較, 爲了 匹配當前層數, 需要把 同一層及以上都pop出stack.
            while (stack.size() >= paths.length)
                // 減去 彈出的str 的長度
                currStrLen -= stack.pop().length();
            // 先在最後加'/', 當出現'.'時, 在去掉最後的'/'
            stack.push(pathOrFileName + "/");
            currStrLen += pathOrFileName.length() + 1;
            if (pathOrFileName.indexOf(".") != -1)
                max = Math.max(max, currStrLen - 1);
        }
        return max;
    }
}
