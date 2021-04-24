package cn.buoy.leetcode.math;

public class Q231 {
    /**
     * https://www.youtube.com/watch?v=8qy2W5xNgZk
     */
    public boolean isPowerOfTwo(int n) {
        //2的0次方最小是1
        return n > 0
                //2的次方都是只有1个1, 与 n- 1 即 比如011, 刚好是0.
                && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n < 1) return false;
        while (n % 2 == 0)
            n /= 2;
        return n == 1;
    }
}
