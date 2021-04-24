package cn.buoy.leetcode.math;

public class Q342 {
    /**
     * https://leetcode.com/problems/power-of-four/discuss/80460/1-line-C%2B%2B-solution-without-confusing-bit-manipulations
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        //(num-1) % 3 == 0 刚好可以处理 1 % 4 的情况!
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    /**
     * https://www.youtube.com/watch?v=_EKIsrWMQkQ
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour2(int n) {
        //关键如何处理 1 和 特例.
        if (n > 1)
            while (n % 4 == 0) n /= 4;
        return n == 1;
    }
}
