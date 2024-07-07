package cn.buoy.leetcode.stackandpq;

import java.util.Stack;

public class Q394 {
    /**
     * 简单, 視頻, 代碼
     * https://www.youtube.com/watch?v=JXlosO-4BSI
     * 思路: 有括號的, 要想到 stack, 需要 壓棧 的有 數 和 前邊的字(已處理, 沒有 []),
     * 當出現 '[', 壓棧,
     * ']' 彈出一個 數字 和 str, 處理.
     * ....
     */
    public String decodeString(String s) {
        // 存 數字
        Stack<Integer> intStack = new Stack<>();
        // 存 '[', ']', 'a~z'
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curStr = new StringBuilder();
        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            } else if (ch == '[') {
                // 數字 push
                intStack.push(num);
                // str push
                strStack.push(curStr);
                //开辟新的 [] 字串.
                curStr = new StringBuilder();
                num = 0;
            } else if (ch == ']') { //先完成 [] 內拼接, 弹出 前边入棧的 未完成的 str 与 刚拼好的 str append.
                StringBuilder tmp = curStr;
                // 前边入棧的 未完成的 str, 這部分的str已經扁平化.
                curStr = strStack.pop();
                //弹出 num, 重复 num 次,
                for (num = intStack.pop(); num > 0; --num)
                    curStr.append(tmp);
            } else
                curStr.append(ch);
        }
        return curStr.toString();
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        Q394 q394 = new Q394();
        String s1 = q394.decodeString(s);
        System.out.println(s1);
    }
}
