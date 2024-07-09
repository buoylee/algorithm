package cn.buoy.leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class Q151 {
    /**
     * 簡單, 視頻, 代碼
     * https://www.youtube.com/watch?v=J_gwr2llhBw
     * 用 s.trim().split("\\s+") 提取出 所有 word
     */
    public String reverseWords(String s) {
        // 提取出 所有 word
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        // 從後往前 append word
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            // 最後一個 word 不用補 " "
            if (i != 0)
                result.append(" ");
        }
        return result.toString();
    }

    /**
     * 這個方法也行, 不用正則. stack.
     */
    public String reverseWords2(String s) {
        int left = 0, right = s.length() - 1;
        // Remove leading and trailing spaces
        while (left <= right && s.charAt(left) == ' ') left++;
        while (left <= right && s.charAt(right) == ' ') right--;
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        // Push words into stack
        while (left <= right) {
            char c = s.charAt(left);
            // 遇到 ' ', 就把 word 放入 stack
            if (c == ' ' && word.length() != 0) {
                stack.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ')
                word.append(c);
            // 單純用作 for 遍歷 i 一樣.
            left++;
        }
        // 最後一個word, 因爲之前 trim 掉了 首位空格, 需要手動再 push 一次.
        stack.offerFirst(word.toString());
        return String.join(" ", stack);
    }

    // 太長, 跳過
    public String reverseWords3(String s) {
        if (s == null) return null;
        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;
        //j都会在i后边, 他们之间肯定是字母
        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            //跳词前空格
            while (j < n && a[j] == ' ') j++;             // skip spaces
            //移单词到前头, 等于删掉空格.
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            //在跳单下个单词开头
            while (j < n && a[j] == ' ') j++;             // skip spaces
            //先插一个空格, 再继续.
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
}
