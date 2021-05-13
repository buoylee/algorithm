package cn.buoy.leetcode.stackandpq;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q388 {
    /**
     * https://www.youtube.com/watch?v=9ndZEOEjU2k
     * 注意边界, 思路简单.
     */
    public int lengthLongestPath(String input) {
        Deque<Integer> stack = new ArrayDeque<>();
        //拿来弹的
        stack.push(0); // "dummy" length
        int maxLen = 0;
        for (String s : input.split("\n")) {
            //数 有几个`\t`
            int lev = s.lastIndexOf("\t") + 1; // number of "\t"
            //第一次就弹了
            //1 1
            while (lev + 1 < stack.size()) stack.pop(); // find parent
            //`/t`是1个str, 去`\t`, 补`/`
            int len = stack.peek() + s.length() - lev + 1; // remove "/t", add"/"
            stack.push(len);
            // check if it is file
            //出现`.`, 即文件, update maxLen
            if (s.contains(".")) maxLen = Math.max(maxLen, len - 1);
        }
        return maxLen;
    }
}
