package cn.buoy.leetcode.stackandpq;

import java.util.*;

/**
 * 简单, 視頻
 * https://www.youtube.com/watch?v=SxuGQnlsXcw
 * 思路: 用 / 分隔, 用 stack 存儲,
 * 對 "..", ".", " " 加以判斷, 最後注意 res 空時, 返回 "/"
 */
public class Q71 {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));

        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty())
                stack.pop();
            else if (!skip.contains(dir))
                stack.push(dir);
        }
        String res = "";
        for (String dir : stack)
            res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }
}
