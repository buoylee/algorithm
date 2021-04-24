package cn.buoy.leetcode.math;

public class Q326 {
    /**
     * https://www.youtube.com/watch?v=bEJQBEsUPgk
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        //关键如何处理 1 和 特例.
        if (n > 1)
            while (n % 3 == 0) n /= 3;
        return n == 1;
    }
}
