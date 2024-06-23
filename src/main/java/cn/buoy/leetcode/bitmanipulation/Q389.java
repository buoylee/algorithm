package cn.buoy.leetcode.bitmanipulation;

public class Q389 {
    /**
     * 超簡單,
     * https://www.youtube.com/watch?v=ctd8TZI8uL0
     * 視頻是另一解法, 沒必要
     * <p>
     * 思路: 相同的数, 异或运算 == 0. 把2string每個字母全部亦或, 即得到多出來那一個字母.
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
