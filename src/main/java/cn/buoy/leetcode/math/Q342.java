package cn.buoy.leetcode.math;

public class Q342 {
    /**
     * https://www.youtube.com/watch?v=_EKIsrWMQkQ
     *
     */
    public boolean isPowerOfFour2(int n) {
        //关键如何处理 1 和 負數 0.
        if (n > 1)
            while (n % 4 == 0)
                n /= 4;
        return n == 1;
    }

    /**
     * 基礎對數計算
     * 細節: % 1 用來 排除 分數. 1.5 % 1 == 0.5
     */
    public boolean isPowerOfFour3(int num) {
        return (Math.log(num) / Math.log(4)) % 1 == 0;
    }

    /**
     * https://leetcode.com/problems/power-of-four/discuss/80460/1-line-C%2B%2B-solution-without-confusing-bit-manipulations
     */
    public boolean isPowerOfFour(int num) {
        //(num-1) % 3 == 0 刚好可以处理 1 % 4 的情况!
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }
}
