package cn.buoy.leetcode.stackandpq;


import cn.buoy.leetcode.NestedInteger;

import java.util.List;
import java.util.Stack;

public class Q385 {
    /**
     * https://www.youtube.com/watch?v=wMoylXlBik4
     * 看視頻, 好理解.
     * 思路:
     * '[': 開啓一個node;
     * ']': add 最後一個數字, 然後放到 node 裏, 然後把這個完整的 int[], 放入到'上層 node'(存在的話).
     * ',': 如果 ','前邊是一個']', 不用處理, 因爲前邊處理過了, 如果是'數字', 需要 add 到 node 中.
     */
    public NestedInteger deserialize(String s) {
        if (s.isEmpty())
            return null;
        if (s.charAt(0) != '[') // ERROR: special case
            return new NestedInteger(Integer.valueOf(s));
        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger currNode = null;
        // 一個數字的最左邊index.
        int numLeftIndex = 0; // numLeftIndex shall point to the start of a number substring;
        // currentIndex shall point to the end+1 of a number substring
        for (int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
            char ch = s.charAt(currentIndex);
            // 關鍵: 出现 [ 创建 NestedInteger, 然後加入 stack.
            if (ch == '[') {
                // 關鍵: 只要第一層, 才需要馬上加入 stack.
                if (currNode != null) {
                    stack.push(currNode);
                }
                currNode = new NestedInteger();
                numLeftIndex = currentIndex + 1;
            } else if (ch == ']') {
                // 思路: 一個arr的結尾, 需要
                //加入 ] 前邊的那個數字.
                String num = s.substring(numLeftIndex, currentIndex);
                // 空str 排除.
                if (!num.isEmpty())
                    currNode.add(new NestedInteger(Integer.valueOf(num)));
                // 關鍵: 如果栈有值(有上一層array), 弹出, 然后将 curr node 加入到弹出的 NestedInteger.
                if (!stack.isEmpty()) {
                    NestedInteger pop = stack.pop();
                    pop.add(currNode);
                    // 關鍵: 上層 成爲 當前層, 繼續後續檢查.
                    currNode = pop;
                }
                numLeftIndex = currentIndex + 1;
            } else if (ch == ',') {
                //, 前一位 只有 数字 或者 ], 如果是 ], 不需要處理; 排除 ] 后, 加入 NestedInteger.
                if (s.charAt(currentIndex - 1) != ']') {
                    String num = s.substring(numLeftIndex, currentIndex);
                    currNode.add(new NestedInteger(Integer.valueOf(num)));
                }
                numLeftIndex = currentIndex + 1;
            }
            // 還有一種情況, 就是'數字', 什麼都不用做, 只需要等 currentIndex++, 找到一個 非數字的 index, 才會真正處理這個數字.
        }
        return currNode;
    }

    // 有時間再看
    // dfs
    public NestedInteger deserialize2(String s) {
        NestedInteger ret = new NestedInteger();
        if (s == null || s.length() == 0) return ret;
        if (s.charAt(0) != '[') {
            ret.setInteger(Integer.parseInt(s));
        } else if (s.length() > 2) {
            int start = 1, count = 0;
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                if (count == 0 && (c == ',' || i == s.length() - 1)) {
                    ret.add(deserialize2(s.substring(start, i)));
                    start = i + 1;
                } else if (c == '[') count++;
                else if (c == ']') count--;
            }
        }
        return ret;
    }
}


