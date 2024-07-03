package cn.buoy.leetcode.math;

public class Q50 {
    /**
     * https://www.youtube.com/watch?v=b0__dHwovLQ
     * https://www.youtube.com/watch?v=ZTACajQOb2E 這個容易理解
     * 思路: 分治法, 每次都分2半來處理.
     */
    public static double myPow(double x, int n) {
        if (n > 0)
            return helper(x, n);
        else // 指數爲負, 就是'倒數'.
            return 1.0 / helper(x, n);
    }

    // 分治法, 將 x的n次方 變成 x的n/2次方 * x的n/2次方, 奇數需要補 *x
    public static double helper(double x, int n) {
        if (n == 0)
            return 1;
        // 將 n 分成2半處理
        double y = helper(x, n / 2);
        if (n % 2 == 0)
            return y * y;
        else // 如果是奇數, 補回余下的1個 x.
            return y * y * x;
    }

    /**
     * https://www.youtube.com/watch?v=QWphd0kFVh4
     * 不好理解, 先跳過.
     */
    public double myPow2(double x, int n) {
        if (x == 0) return 0;
        //转long, n不会越界
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
//            if ((b % 2) == 1) res *= x;
            if ((b & 1) == 1)
                res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
