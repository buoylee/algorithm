package cn.buoy.leetcode.math;

public class Q29 {
    /**
     * 簡單, 明白記住, 看視頻, 然後看註釋.
     * https://www.youtube.com/watch?v=XKuFGEGt5zo
     * https://www.youtube.com/watch?v=uD1Cw1JbD8E 原理看這個
     * 思路: 轉化爲計算 dividend 有幾個 divisor
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        int res = 0;
        //用抑或求符号
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        //被除数 减去 (除数 * 2的n次方, 不大于被除数), 反复上述操作, 直至 divisor 大於 剩下的dividend.
        while (dvs <= dvd) {
            // divisor 不斷*2
            long tempDivisor = dvs;
            // 2的次方數(也是不斷*2)
            long mul = 1;
            //  divisor 不斷 *2, 直到 divisor 大於 dividend
            // 假如 下一次 tempDivisor*2 後, 使得 dvd >= tempDivisor, 就跳出循環
            while (dvd >= tempDivisor << 1) {
                tempDivisor <<= 1;
                mul <<= 1;
            }
            // 減去 mul 個 divisor. 繼續檢查還有幾個 divisor. 直到剩下的 dividend 小於 divisor.
            dvd -= tempDivisor;
            // 累積起來就是 商.
            res += mul;
        }
        return sign == 1 ? res : -res;
    }

    public static void main(String[] args) {
        new Q29().divide(11, 3);
    }
}