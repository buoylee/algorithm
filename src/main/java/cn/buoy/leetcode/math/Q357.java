package cn.buoy.leetcode.math;

public class Q357 {
    /**
     * 簡單, 視頻.
     * 題目怪怪的, n 代表 數的位數.
     * https://www.youtube.com/watch?v=MY4V-SQAkF4
     * 思路: 典型排列組合, 找規律(看下邊), 累積 1~n的數量就是解.
     * 1位: 10 即(0~9)
     * 2:开头要除0的数: 9 * 9
     * 3:开头要除0的数: 9 * 9 * 8
     * 4:开头要除0的数: 9 * 9 * 8 * 7
     * ...
     */
    public int countNumbersWithUniqueDigits(int n) {
        int res = 0;
        // 從 1位 開始累加 到 n
        for (int i = 1; i <= n; i++)
            res += countNumbers(i);
        return res + 1;
    }

    /**
     * 統計 n位數 不同數字組成的數的 數量
     */
    private int countNumbers(int n) {
        int res = 9;
        // 首位 一定是9
        int base = 9;
        // 9 * 9 * 8 * 7 * 6...
        while (n > 1 && base > 1) {
            res *= base;
            base--;
            n--;
        }
        return res;
    }

    /**
     * 思路一樣, 更巧妙. 代碼超少
     */
    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) return 1;
        int result = 10;
        int uniqueDigits = 9;
        // 剩餘可選數字個數
        int availableNums = 9;
        // 巧妙: 下邊循環是從 2位開始算, 3位, 4位..., n 由 x 倒數到 1.
        while (n-- > 1 && availableNums > 0) {
            // 2位爲起點.
            uniqueDigits = uniqueDigits * availableNums;
            // result == the number of (2位 + 3位 + 4位...)
            result += uniqueDigits;
            availableNums--;
        }
        return result;
    }
}
