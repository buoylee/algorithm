package cn.buoy.leetcode.math;

public class Q357 {
    /**
     * https://www.youtube.com/watch?v=MY4V-SQAkF4
     * 算不重复数字:
     * 1位: 0~9
     * 2:开头要除0的数: 9 * 9
     * 3:开头要除0的数: 9 * 9 * 8
     * ...
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;

        int res = 10;
        int uniqueDigits = 9;
        int availableNumber = 9;
        while (n-- > 1 && availableNumber > 0) {
            uniqueDigits = uniqueDigits * availableNumber;
            res += uniqueDigits;
            availableNumber--;
        }
        return res;
    }
}
