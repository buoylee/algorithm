package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q394 {
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Q394 q394 = new Q394();
        String s1 = q394.decodeString(s);
        System.out.println(s1);
    }

    /**
     * 还是简单!
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                //转数字, 假如不至一位数字, 需要累加每一位
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                //遇到重复头, 将 累加数, 前边完成的字符 分别入栈
                intStack.push(k);
                strStack.push(cur);
                //开辟新空间 拼接 新的 [] 字串
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                //完成拼接, 弹出前边完成的字串与 刚拼好的string append.
                StringBuilder tmp = cur;
                //同时弹出 重复次数 int,
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k)
                    cur.append(tmp);
            } else
                cur.append(ch);
        }
        return cur.toString();
    }
}
