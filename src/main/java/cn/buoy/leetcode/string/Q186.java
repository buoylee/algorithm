package cn.buoy.leetcode.string;

public class Q186 {
    public void reverseWords(char[] str) {
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
