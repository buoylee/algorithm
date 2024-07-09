package cn.buoy.leetcode.string;

public class Q345 {
    /**
     * 到底是什麼弱智啊
     * https://www.youtube.com/watch?v=eBIi3jge4xg
     * 思路: 頭尾双指针, 跳過輔音找元音 char.
     */
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        String vowels = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && !vowels.contains(chars[start] + ""))
                start++;
            while (start < end && !vowels.contains(chars[end] + ""))
                end--;
            // swap
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
