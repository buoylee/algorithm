package cn.buoy.leetcode.stackandpq;


import cn.buoy.leetcode.NestedInteger;

import java.util.List;
import java.util.Stack;

public class Q385 {
    /**
     * 相对还是简单
     *
     * @param s
     * @return
     */
    public NestedInteger deserialize(String s) {
        if (s.isEmpty())
            return null;
        if (s.charAt(0) != '[') // ERROR: special case
            return new NestedInteger(Integer.valueOf(s));

        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger curr = null;
        int l = 0; // l shall point to the start of a number substring;
        // r shall point to the end+1 of a number substring
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            //出现 [ 创建 NestedInteger.
            if (ch == '[') {
                if (curr != null) {
                    stack.push(curr);
                }
                curr = new NestedInteger();
                l = r + 1;
            } else if (ch == ']') {
                //前一位是数字就加入.
                String num = s.substring(l, r);
                if (!num.isEmpty())
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                //如果栈有值, 弹出, 然后将 curr add入到 弹出NestedInteger中.
                if (!stack.isEmpty()) {
                    NestedInteger pop = stack.pop();
                    pop.add(curr);
                    curr = pop;
                }
                l = r + 1;
            } else if (ch == ',') {
                //, 前一位 只有 数字 或者 ], 排除 ] 后, add 进 NestedInteger.
                if (s.charAt(r - 1) != ']') {
                    String num = s.substring(l, r);
                    curr.add(new NestedInteger(Integer.valueOf(num)));
                }
                l = r + 1;
            }
        }

        return curr;
    }
}


