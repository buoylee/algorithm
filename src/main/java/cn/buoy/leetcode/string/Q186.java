package cn.buoy.leetcode.string;

import java.util.Stack;

public class Q186 {
    /**
     * 簡單, 代碼; 或者看第2種解法有視頻.
     * 思路: stack, 遇到 ' ' 就把 前一個 word push in stack, 最後再放回 char[].
     */
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0)
            return;
        Stack<String> stack = new Stack<>();
        int wordStartIdx = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                stack.push(new String(s, wordStartIdx, i - wordStartIdx));
                // 跳過 ' ' 就是 word 開頭
                wordStartIdx = i + 1;
            }
        }
        // 也可以參考下邊加 判斷 end == s.length 寫法.
        // 最后一个单词
        stack.push(new String(s, wordStartIdx, s.length - wordStartIdx));
        int resultIdx = 0;
        while (!stack.isEmpty()) {
            String word = stack.pop();
            // 拆 str 成 char
            for (char c : word.toCharArray())
                s[resultIdx++] = c;
            // 放 ' '
            if (resultIdx < s.length)
                s[resultIdx++] = ' ';
        }
    }

    /**
     * 簡單, 視頻, 代碼和視頻在處理最後一個 word 不太一樣,
     * https://www.youtube.com/watch?v=voWDMaKc-Lo
     * 思路: 1. 翻转整个字符串; 2. 翻转每个单词
     */
    public void reverseWords2(char[] str) {
        if (str == null || str.length == 0)
            return;
        // 1. 翻转整个字符串
        reverse(str, 0, str.length - 1);

        int strIdx = 0;
        // word 的 wordStartIdx index
        int wordStartIdx = 0;
        // 因爲 str 末尾可能沒有 ' ', 所以 增加一個  strIdx == str.length, 判斷 是否 末尾的 word
        while (strIdx <= str.length) {
            // 2. 遇到' ', 可以翻转 前邊一個 word
            if (strIdx == str.length || str[strIdx] == ' ') {
                reverse(str, wordStartIdx, strIdx - 1);
                // 跳過 一個 ' ' 就是 word 開頭
                wordStartIdx = strIdx + 1;
            }
            strIdx++;
        }
    }

    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public void reverseWords3(char[] str) {
        //reverse the whole string
        helper(str, 0, str.length - 1);

        //reverse every single word
        int start = 0, end = 0;
        //这里的简化, 因为题目的前提.
        while (end < str.length) {
            start = end;
            while (end < str.length && str[end] != ' ') end++;
            helper(str, start, end - 1);
            end++;
        }
    }

    public void helper(char[] str, int i, int j) {
        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
            i++;
            j--;
        }
    }
}
