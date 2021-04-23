package cn.buoy.leetcode.math;

public class Q50 {
    /**
     * https://www.youtube.com/watch?v=QWphd0kFVh4
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
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
