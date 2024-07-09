package cn.buoy.leetcode.string;

import java.util.Stack;

public class Q32 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=M1Vw5Tk1rw4 stack
     * https://www.youtube.com/watch?v=7sLZoD05uxw dp/stack, 這 2種 stack 思路一樣, 差別在於如何處理 無法匹配的')'.
     * 思路: 當普通 stack處理 "()" 來做, push 的是 "()" 對應的 index, pop 完匹配的 '(' 後, 計算 i - stack.peek(), 就可以拿來比較 maxLen.
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        // 關鍵: 1. 當所有 '(', ')' 都彈出時, 便於計算當前最長長度.
        // 關鍵: 2. 當連 '-1' 都被 pop, 說明有多一個')'出現, 即 不合法, 我們需要對'後續元素' 重新累積 長度.
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // push '('
            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                // i 是 ')', 就 pop 一個 '('.
                stack.pop();
                if (stack.isEmpty())
                    stack.push(i);
                else
                    maxLen = Math.max(maxLen, i - stack.peek());
            }
        }
        return maxLen;
    }

    // 比 上邊 對一個變量 left(左邊界). 其實是不需要的.
    public int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        // 關鍵: 合法的'左邊界-1'; 當遇到無法匹配的')', 就要重新開始累積長度.
        int left = -1;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '(') stack.push(j);
            else {
                // 遇到不合法的')', 更新 left
                if (stack.isEmpty()) left = j;
                else {
                    stack.pop();
                    if (stack.isEmpty()) max = Math.max(max, j - left);
                    else max = Math.max(max, j - stack.peek());
                }
            }
        }
        return max;
    }

    /**
     * dp
     * 看視頻解釋, 就很好懂. dp代碼不好寫.
     */
    public int longestValidParentheses3(String s) {
        int maxLength = 0;
        // 關鍵: dp[i]指的是, 到達i(i這個位置一定要合法) 從 0~i, 在這個substr合法的長度;
        // 例如: "())()" dp == 2; 最後2位才能組成 到達i包括i 連貫的substr.
        // "()(", dp == 0, 包括i的情況下, 無法組成合法 substr.
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // 如果 i - 1 是 '(', 他們自己就組成了1對"()", 所以 只要 dp[i - 2] + 2 就是 dp[i]
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') // 如果 i - 1 也是 ')', 那我們就要幫 i 即')', 找到與他配對的'(', 其實就是 i - dp[i - 1] - 1, 因爲 dp[i - 1] 就是 i - 1 能匹配的長度, 再 -1, 就是 i 匹配的 '('(如果剛好是'('的話.)
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                maxLength = Math.max(maxLength, dp[i]);
            }
        }
        return maxLength;
    }
}
