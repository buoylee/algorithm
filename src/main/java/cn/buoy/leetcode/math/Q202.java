package cn.buoy.leetcode.math;

import java.util.HashSet;
import java.util.Set;

public class Q202 {
    /**
     * https://www.youtube.com/watch?v=TfTv4G8qrjs
     * 找环, 如果不等于1, 也可以
     */
    public boolean isHappy(int n) {
        Set<Integer> inLoop = new HashSet<Integer>();
        int squareSum, remain;
        //如果不是 1 退出, 就 不是 happy num.
        while (inLoop.add(n)) {
            squareSum = 0;
            while (n > 0) {
                remain = n % 10;
                squareSum += remain * remain;
                n /= 10;
            }
            if (squareSum == 1)
                return true;
            else
                n = squareSum;

        }
        return false;

    }
}
