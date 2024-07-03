package cn.buoy.leetcode.math;

public class Q258 {
    /**
     * 簡單, 視頻
     * https://www.youtube.com/watch?v=7ab7S8wvy0s
     * 思路: 就一直 拆digit, 然後sum, 循環. 直到 num < 10.
     * <p>
     * 下邊解法比較容易寫出來.
     */
    public int addDigits(int num) {
        while (num >= 10) {
            // 完成一次 number 的所有 digits 相加.
            int sum = 0;
            while (num != 0) {
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }
        return num;
    }

    /**
     * 視頻代碼
     */
    public int addDigits2(int num) {
        if (num < 10)
            return num;
        int res = 0;
        // 完成一次 number 的所有 digits 相加.
        while (num != 0) {
            res += num % 10;
            num /= 10;
        }
        return addDigits2(res);
    }


}
