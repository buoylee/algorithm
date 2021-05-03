package cn.buoy.leetcode.bitmanipulation;

public class Q389 {
    /**
     * https://www.youtube.com/watch?v=1gZDA3zmlYY
     * 相同的数, 异或 运算 相同 -> 0; 不同 -> 1.
     */
    public char findTheDifference(String s, String t) {
        char c = 0;
        for (int i = 0; i < s.length(); ++i) {
            c ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            c ^= t.charAt(i);
        }
        return c;
    }
}
