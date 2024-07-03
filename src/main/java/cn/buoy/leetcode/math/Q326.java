package cn.buoy.leetcode.math;

public class Q326 {
    /**
     * 弱智, 注意 n = 1 也是解.
     * https://www.youtube.com/watch?v=bEJQBEsUPgk
     * 一直除3, 直到不能整除, 商是1 就是 3的次方數. 3^0 == 0 也是合法的.
     */
    public boolean isPowerOfThree(int n) {
        // 等於1 就退出了.
        if (n < 1) return false;
        while (n % 3 == 0)
            n /= 3;
        return n == 1;
    }
}
