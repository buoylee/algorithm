package cn.buoy.leetcode.math;

public class Q29 {
    /**
     * https://www.youtube.com/watch?v=XKuFGEGt5zo
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int res = 0;
        //用抑或求符号
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        //被除数 减去 (除数 * 2的n次方, 不大于被除数), 反复上述操作, 直至被除数 小于 除数.
        while (dvs <= dvd) {
            //temp 临时的 倍数
            //mul 2的次方
            long temp = dvs, mul = 1;
            while (dvd >= temp << 1) {
                temp <<= 1;
                mul <<= 1;
            }
            dvd -= temp;
            res += mul;
        }
        return sign == 1 ? res : -res;
    }
}