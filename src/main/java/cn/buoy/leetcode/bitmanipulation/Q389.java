package cn.buoy.leetcode.bitmanipulation;

public class Q389 {
    /**
     * 超簡單, 視頻是 "统计 char 数量", 沒必要
     * https://www.youtube.com/watch?v=ctd8TZI8uL0
     * 思路: 相同的数, 异或运算 == 0. 把 2个 string 每個 char 全部 异或, 即得到多出來那一個字母.
     */
    public char findTheDifference(String s, String t) {
        char c = 0;
        for (int i = 0; i < s.length(); ++i)
            c ^= s.charAt(i);
        for (int i = 0; i < t.length(); ++i)
            c ^= t.charAt(i);
        return c;
    }
}
