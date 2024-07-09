package cn.buoy.leetcode.string;

public class Q12 {
    /**
     * 簡單, 背, 應該不會考, 跳過
     * https://www.youtube.com/watch?v=ODcQYUOB5fA
     */
    public static String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        // M = 千位; C = 百位; X = 十位; I = 個位
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
